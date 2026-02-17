package ar.com.gha.gateway.infrastructure.out.config;

import ar.com.gha.gateway.infrastructure.in.filters.AuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final AuthFilter authFilter;
    @Value("${gateway.auth.auth-server-uri}")
    private String AUTH_VALIDATE_URI;
    @Value("${gateway.services.heroes-ms-uri}")
    private String HEROES_MS_URI;

    @Bean
    @Profile(value="oauth2")
    public RouteLocator routerLocatorOauth2(RouteLocatorBuilder builder){
        return builder
                .routes()
                .route(route -> route
                        .path("/api/v1/heroes-ms/**")
                        .filters(filter -> {
                            filter.filter(this.authFilter);
                            return filter;
                        })
                        .uri(HEROES_MS_URI)
                )
//                .route(route -> route
//                        .path("/report-ms/report/**")
//                        .filters(filter -> filter.filter(this.authFilter))
//                        .uri("lb://report-ms")
//                )
//                .route(route -> route
//                        .path("/companies-crud-fallback/company/**")
//                        .filters(filter -> filter.filter(this.authFilter))
//                        .uri("lb://companies-crud-fallback")
//                )
                .route(route -> route
                        .path("/auth-server/auth/**")
                        .uri(AUTH_VALIDATE_URI)
                )
                .build();
    }

}
