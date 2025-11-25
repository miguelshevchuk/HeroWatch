package ar.com.gha.heroes.infrastructure.config;

import ar.com.gha.heroes.application.usecase.CreateHeroeUseCase;
import ar.com.gha.heroes.application.usecase.ListHeroesUseCase;
import ar.com.gha.heroes.domain.port.HeroEventPublisher;
import ar.com.gha.heroes.domain.port.HeroeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

    @Bean
    public ListHeroesUseCase listHeroesUseCase(HeroeRepository heroeRepository) {
        return new ListHeroesUseCase(heroeRepository);
    }

    @Bean
    public CreateHeroeUseCase createHeroeUseCase(HeroeRepository heroeRepository, HeroEventPublisher publisher) {
        return new CreateHeroeUseCase(heroeRepository, publisher);
    }
}
