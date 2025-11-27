package ar.com.gha.heroesms.application.usecase;

import ar.com.gha.heroesms.domain.command.UpdateHeroeCommand;
import ar.com.gha.heroesms.domain.event.HeroEvent;
import ar.com.gha.heroesms.domain.model.Heroe;
import ar.com.gha.heroesms.domain.port.HeroEventPublisher;
import ar.com.gha.heroesms.domain.port.HeroeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class UpdateHeroeUseCase {

    private final HeroeRepository heroeRepository;
    private final HeroEventPublisher eventPublisher;

    public Heroe execute(UpdateHeroeCommand cmd) {

        if (cmd == null) throw new IllegalArgumentException("Command requerido");

        heroeRepository.findByAlias(cmd.alias()).orElseThrow(
                () -> new IllegalArgumentException("No existe el heroe con el alias: " + cmd.alias())
        );

        Heroe heroe = Heroe.create(
                cmd.id(),
                cmd.alias(),
                cmd.nombreReal(),
                cmd.estado(),
                cmd.nivelEnergia(),
                cmd.poderes()
        );

        Heroe saved = heroeRepository.update(heroe);

        List<String> poderes = saved.getPoderes() == null ? null :
                StreamSupport.stream(saved.getPoderes().spliterator(), false)
                        .map(p -> p.getPoder() == null ? null : p.getPoder().value())
                        .collect(Collectors.toList());

        HeroEvent event = new HeroEvent(
                saved.getId(),
                saved.getAlias() == null ? null : saved.getAlias().value(),
                saved.getNombreReal() == null ? null : saved.getNombreReal().value(),
                String.valueOf(saved.getEstado()),
                saved.getNivelEnergia() == null ? null : saved.getNivelEnergia().value(),
                poderes,
                Instant.now(),
                EVENT_TYPE.HERO_UPDATED.name()
        );
        eventPublisher.publishHeroUpdated(event);

        return saved;
    }

}
