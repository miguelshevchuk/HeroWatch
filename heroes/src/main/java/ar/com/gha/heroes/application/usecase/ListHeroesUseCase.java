package ar.com.gha.heroes.application.usecase;

import ar.com.gha.heroes.domain.model.Heroe;
import ar.com.gha.heroes.domain.repository.HeroeRepository;

public class ListHeroesUseCase {

    private final HeroeRepository heroeRepository;

    public ListHeroesUseCase(HeroeRepository heroeRepository) {
        this.heroeRepository = heroeRepository;
    }

    public Iterable<Heroe> execute() {
        return heroeRepository.findAll();
    }
}
