package ar.com.gha.heroes.application.usecase;

import ar.com.gha.heroes.domain.model.Heroe;
import ar.com.gha.heroes.domain.port.HeroeRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ListHeroesUseCase {

    private final HeroeRepository heroeRepository;

    public Iterable<Heroe> execute() {
        return heroeRepository.findAll();
    }
}
