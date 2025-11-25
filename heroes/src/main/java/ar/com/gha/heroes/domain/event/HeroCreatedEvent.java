package ar.com.gha.heroes.domain.event;

import java.time.Instant;
import java.util.List;

public record HeroCreatedEvent(
        Integer id,
        String alias,
        String nombreReal,
        String estado,
        Integer nivelEnergia,
        List<String> poderes,
        Instant timestamp
) {}
