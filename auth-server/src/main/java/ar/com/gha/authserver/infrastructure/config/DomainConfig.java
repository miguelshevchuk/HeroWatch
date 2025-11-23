package ar.com.gha.authserver.infrastructure.config;

import ar.com.gha.authserver.domain.service.UserValidatorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

    @Bean
    public UserValidatorService userValidatorService() {
        return new UserValidatorService();
    }

}
