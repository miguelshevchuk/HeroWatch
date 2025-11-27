package ar.com.gha.heroesms.domain.port;


import ar.com.gha.heroesms.domain.event.HeroEvent;

public interface HeroEventPublisher {
    void publishHeroCreated(HeroEvent event);
    void publishHeroUpdated(HeroEvent event);
}
