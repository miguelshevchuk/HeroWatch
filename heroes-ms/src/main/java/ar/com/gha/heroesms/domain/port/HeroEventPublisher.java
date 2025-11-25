package ar.com.gha.heroesms.domain.port;


import ar.com.gha.heroesms.domain.event.HeroCreatedEvent;

public interface HeroEventPublisher {
    void publishHeroCreated(HeroCreatedEvent event);
}
