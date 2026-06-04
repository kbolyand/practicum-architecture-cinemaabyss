package cinema.proxy.func;

import cinema.proxy.config.GatewayProperties;

import java.net.URI;
import java.util.concurrent.ThreadLocalRandom;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
@AllArgsConstructor
@Slf4j
public class GatewayConfig {
    private final GatewayProperties properties;

    @Bean
    public RouterFunction<ServerResponse> gatewayRoutes() {
        return GatewayRouterFunctions.route("movies-gateway")
                .route(RequestPredicates.path("/health"), _ -> ServerResponse.ok().body("OK"))
                .route(RequestPredicates.path("/api/movies"), _ -> ServerResponse.permanentRedirect(URI.create(chooseTarget())).build())
                .route(RequestPredicates.path("/api/movies/**"), _ -> ServerResponse.permanentRedirect(URI.create(chooseTarget())).build())
                .route(RequestPredicates.all(), _ -> ServerResponse.permanentRedirect(URI.create(properties.getMonolithUrl())).build())
                .build();
    }

    private String chooseTarget() {
        if (!properties.isGradualMigration()) {
            return properties.getMonolithUrl();
        }
        int random = ThreadLocalRandom.current().nextInt(100);
        if (random < properties.getMoviesMigrationPercent()) {
            return properties.getMoviesServiceUrl();
        }
        return properties.getMonolithUrl();
    }

}
