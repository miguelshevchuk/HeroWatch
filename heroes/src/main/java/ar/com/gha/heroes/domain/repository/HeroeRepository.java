package ar.com.gha.heroes.domain.repository;

import ar.com.gha.heroes.domain.model.Heroe;


public interface HeroeRepository {

    Iterable<Heroe> findAll();

}
