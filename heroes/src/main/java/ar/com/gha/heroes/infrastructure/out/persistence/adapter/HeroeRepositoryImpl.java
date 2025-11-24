package ar.com.gha.heroes.infrastructure.out.persistence.adapter;

import ar.com.gha.heroes.domain.model.Heroe;
import ar.com.gha.heroes.domain.repository.HeroeRepository;
import ar.com.gha.heroes.infrastructure.out.persistence.adapter.crud.HeroeCrudRepository;
import ar.com.gha.heroes.infrastructure.out.persistence.mapper.HeroeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class HeroeRepositoryImpl implements HeroeRepository {

    private HeroeCrudRepository heroeCrudRepository;

    @Override
    public Iterable<Heroe> findAll() {
        return HeroeMapper.INSTANCE.toDomain(heroeCrudRepository.findAll());
    }
}
