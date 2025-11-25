package ar.com.gha.heroes.domain.port;

import ar.com.gha.heroes.domain.model.Heroe;

import java.util.Optional;

public interface HeroeRepository {

    Iterable<Heroe> findAll();

    Heroe save(Heroe heroe);

    Optional<Heroe> findByAlias(String alias);
}
