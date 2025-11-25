package ar.com.gha.heroesms.application.usecase;


import ar.com.gha.heroesms.domain.command.CreateHeroeCommand;
import ar.com.gha.heroesms.domain.event.HeroCreatedEvent;
import ar.com.gha.heroesms.domain.model.Heroe;
import ar.com.gha.heroesms.domain.port.HeroEventPublisher;
import ar.com.gha.heroesms.domain.port.HeroeRepository;
import ar.com.gha.heroesms.infrastructure.input.rest.exception.DuplicateAliasException;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
public class CreateHeroeUseCase {

    private final HeroeRepository heroeRepository;
    private final HeroEventPublisher eventPublisher;

    public Heroe execute(CreateHeroeCommand cmd) {
        if (cmd == null) throw new IllegalArgumentException("Command requerido");

        this.heroeRepository.findByAlias(cmd.alias()).ifPresent(h -> {
            throw new DuplicateAliasException("El alias ya existe: " + cmd.alias());
        });

        Heroe toCreate = Heroe.create(
                cmd.alias(),
                cmd.nombreReal(),
                cmd.estado(),
                cmd.nivelEnergia(),
                cmd.poderes()
        );

        Heroe saved = heroeRepository.save(toCreate);

        List<String> poderes = saved.getPoderes() == null ? null :
                StreamSupport.stream(saved.getPoderes().spliterator(), false)
                        .map(p -> p.getPoder() == null ? null : p.getPoder().value())
                        .collect(Collectors.toList());

        HeroCreatedEvent event = new HeroCreatedEvent(
                saved.getId(),
                saved.getAlias() == null ? null : saved.getAlias().value(),
                saved.getNombreReal() == null ? null : saved.getNombreReal().value(),
                String.valueOf(saved.getEstado()),
                saved.getNivelEnergia() == null ? null : saved.getNivelEnergia().value(),
                poderes,
                Instant.now()
        );
        eventPublisher.publishHeroCreated(event);
        return saved;
    }
}
