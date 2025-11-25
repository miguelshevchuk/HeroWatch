package ar.com.gha.heroesms.infrastructure.config;

import ar.com.gha.heroesms.application.usecase.CreateHeroeUseCase;
import ar.com.gha.heroesms.application.usecase.ListHeroesUseCase;
import ar.com.gha.heroesms.domain.port.HeroEventPublisher;
import ar.com.gha.heroesms.domain.port.HeroeRepository;
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
