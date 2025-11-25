package ar.com.gha.heroesms.application.usecase;


import ar.com.gha.heroesms.domain.model.Heroe;
import ar.com.gha.heroesms.domain.port.HeroeRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ListHeroesUseCase {

    private final HeroeRepository heroeRepository;

    public Iterable<Heroe> execute() {
        return heroeRepository.findAll();
    }
}
