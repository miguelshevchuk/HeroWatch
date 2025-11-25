package ar.com.gha.heroesms.domain.port;


import ar.com.gha.heroesms.domain.model.Heroe;

import java.util.Optional;

public interface HeroeRepository {

    Iterable<Heroe> findAll();

    Heroe save(Heroe heroe);

    Optional<Heroe> findByAlias(String alias);
}
