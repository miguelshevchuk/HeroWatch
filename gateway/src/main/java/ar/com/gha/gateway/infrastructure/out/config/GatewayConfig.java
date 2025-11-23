package ar.com.gha.gateway.infrastructure.out.config;

import ar.com.gha.gateway.infrastructure.in.filters.AuthFilter;
import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Set;

@Configuration
@AllArgsConstructor
public class GatewayConfig {

    private final AuthFilter authFilter;

    @Bean
    @Profile(value="oauth2")
    public RouteLocator routerLocatorOauth2(RouteLocatorBuilder builder){
        return builder
                .routes()
//                .route(route -> route
//                        .path("/companies-crud/company/**")
//                        .filters(filter -> {
//                            filter.filter(this.authFilter);
//                            return filter;
//                        })
//                        .uri("lb://companies-crud")
//                )
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
//                .route(route -> route
//                        .path("/auth-server/auth/**")
//                        .uri("lb://auth-server")
//                )
                .build();
    }

}
