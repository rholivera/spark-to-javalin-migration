package extensions;

import com.example.WebServer;
import com.google.common.net.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

@Slf4j
public class ApiTestExtension implements BeforeAllCallback, AfterAllCallback {

    private WebServer app;

    @Override
    public void beforeAll(final ExtensionContext extensionContext) throws Exception {

        //We put the Thread to sleep because in the integration test we need to wait for previous test server to shut down
        Thread.sleep(1000);

        final int port = 8080;
        app = new WebServer((config) -> config.defaultContentType = MediaType.JSON_UTF_8.toString());
        app.start(port);

        log.info("Javalin server running in port: {} ...", port);

    }

    @Override
    public void afterAll(final ExtensionContext extensionContext) {
        log.info("Javalin server stopped...");
        app.stop();
    }
}