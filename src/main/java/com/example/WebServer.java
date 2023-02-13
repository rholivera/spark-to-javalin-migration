package com.example;

import com.example.utils.ExceptionUtils;
import com.google.common.net.MediaType;
import com.example.exception.ApiException;
import com.example.router.Router;
import com.example.router.ServerStartingHandler;
import com.mercadolibre.json.JsonUtils;
import com.mercadolibre.restclient.util.MeliContext;
import com.mercadolibre.routing.RoutingHelper;
import com.newrelic.api.agent.NewRelic;
import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;
import io.javalin.core.util.Header;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import io.javalin.http.Context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.function.Consumer;

@Slf4j
public class WebServer {
    @Getter
    private final Javalin server;

    public WebServer(Consumer<JavalinConfig> config) {
        server = Javalin.create(config).events(event ->
            event.serverStarting(new ServerStartingHandler())
        );

        log.info("Starting with JsonUtils engine: " + JsonUtils.INSTANCE.getEngine());
        setFilters(server);
        setRouteNotFoundHandler(server);
        setExceptionHandler(server);
        setPingHandler(server);
        server.routes(new Router());
    }

    public WebServer() {
        this(config -> {});
    }

    public void start(int port) {
        this.server.start(port);
    }


    public void stop() {
        this.server.stop();
    }

    private void setPingHandler(Javalin app) {
        app.get("/ping", context -> {
            NewRelic.ignoreTransaction();
            context.status(HttpServletResponse.SC_OK);
            context.header(Header.CONTENT_TYPE, MediaType.PLAIN_TEXT_UTF_8.toString());
            context.result("pong");
        });
    }

    public void setFilters(Javalin app) {
        app.before(context -> {
            try {
                HttpServletRequest request = context.req;
                HttpServletResponse response = context.res;
                MeliContext meliContext = RoutingHelper.buildAndSetMeliContext(request);
                response.addHeader("X-Request-Id", meliContext.getRequestId());
            } catch (Exception e) {
                log.error("[meli_context] Error processing meliContext: ", e);
            }
        });

        app.after(context -> {
            setHeaders(context);
            RoutingHelper.clearRequestId();
        });
    }

    private void setRouteNotFoundHandler(Javalin app) {
        app.error(HttpStatus.SC_NOT_FOUND, context -> {
            context.header("Content-Type", MediaType.JSON_UTF_8.toString());

            context.status(HttpServletResponse.SC_NOT_FOUND);

            ApiException e = new ApiException("route_not_found", String.format("Route %s not found", context.path()), HttpServletResponse.SC_NOT_FOUND);
            context.result(e.toJson());
        });
    }

    private void setExceptionHandler(Javalin app) {
        app.exception(Exception.class, (e, context) -> {
            Throwable t = ExceptionUtils.getFromChain(e, ApiException.class);

            ApiException apiException = t instanceof ApiException ? (ApiException) t : new ApiException("internal_error", "Internal server error", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            log.error(apiException.getDescription(), e);
            context.status(apiException.getStatusCode());
            context.result(apiException.toJson());
            setHeaders(context);
            NewRelic.noticeError(e);
        });
    }

    private void setPingEndpoint(Javalin app) {
        app.get("/ping", context -> {
            NewRelic.ignoreTransaction();
            context.status(HttpServletResponse.SC_OK);
            context.header("Content-Type", MediaType.PLAIN_TEXT_UTF_8.toString());
            context.result("pong");
        });
    }

    private void setHeaders(Context context) {
        if (!context.res.containsHeader(Header.CONTENT_TYPE)) {
            context.header(Header.CONTENT_TYPE, MediaType.JSON_UTF_8.toString());
        }

        if (!context.res.containsHeader(Header.VARY)) {
            context.header(Header.VARY, "Accept,Accept-Encoding");
        }

        if (!context.res.containsHeader(Header.CACHE_CONTROL)) {
            context.header(Header.CACHE_CONTROL, "max-age=0");
        }
    }
}