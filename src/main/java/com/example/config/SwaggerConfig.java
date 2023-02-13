package com.example.config;

import io.javalin.plugin.openapi.OpenApiOptions;
import io.javalin.plugin.openapi.OpenApiPlugin;
import io.javalin.plugin.openapi.ui.ReDocOptions;
import io.javalin.plugin.openapi.ui.SwaggerOptions;
import io.swagger.v3.oas.models.info.Info;
public class SwaggerConfig {

    public static OpenApiPlugin getOpenApiOptions() {
        Info applicationInfo = new Info()
                .title("Advertising resuls augmenter manager Documentation")
                .version("1.0")
                .description("Api responsible for the suggestion  to search-api ");

        OpenApiOptions options = new OpenApiOptions(applicationInfo)
                .path("/swagger-docs")
                .activateAnnotationScanningFor("com.mercadolibre")
                .swagger(new SwaggerOptions("/swagger-ui"))
                .reDoc(new ReDocOptions("/redoc").title("My ReDoc Documentation"));
        return new OpenApiPlugin(options);
    }
}
