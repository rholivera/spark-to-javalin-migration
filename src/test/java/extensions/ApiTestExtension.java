package extensions;

import com.example.router.Router;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import spark.Spark;

@Slf4j
public class ApiTestExtension implements BeforeAllCallback, AfterAllCallback {

    @Override
    public void beforeAll(final ExtensionContext extensionContext) throws Exception {

        //We put the Thread to sleep because in the integration test we need to wait for previous test server to shut down
        Thread.sleep(1000);
        final int port = 8080;
        Spark.port(port);
        new Router().init();
        Spark.awaitInitialization();

        log.info("Spark server running in port: {} ...", port);

    }

    @Override
    public void afterAll(final ExtensionContext extensionContext) {
        Spark.stop();
        log.info("Spark server stopped...");
    }
}