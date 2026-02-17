package ar.com.gha.authserver.infrastructure.config;

import ar.com.gha.authserver.domain.service.UserValidatorService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@AllArgsConstructor
public class DomainConfig {

    public final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public UserValidatorService userValidatorService() {
        return new UserValidatorService(bCryptPasswordEncoder);
    }

}
