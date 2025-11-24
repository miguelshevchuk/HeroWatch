package ar.com.gha.heroes.infrastructure.config;

import ar.com.gha.heroes.application.usecase.ListHeroesUseCase;
import ar.com.gha.heroes.domain.repository.HeroeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

    @Bean
    public ListHeroesUseCase listHeroesUseCase(HeroeRepository heroeRepository) {
        return new ListHeroesUseCase(heroeRepository);
    }
}
