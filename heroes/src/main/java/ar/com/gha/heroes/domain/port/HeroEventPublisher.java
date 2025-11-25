package ar.com.gha.heroes.domain.port;

import ar.com.gha.heroes.domain.event.HeroCreatedEvent;

public interface HeroEventPublisher {
    void publishHeroCreated(HeroCreatedEvent event);
}
