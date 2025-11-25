package ar.com.gha.heroes.infrastructure.out.persistence.adapter;

import ar.com.gha.heroes.domain.model.Heroe;
import ar.com.gha.heroes.domain.port.HeroeRepository;
import ar.com.gha.heroes.infrastructure.out.persistence.adapter.crud.HeroeCrudRepository;
import ar.com.gha.heroes.infrastructure.out.persistence.entity.HeroeEntity;
import ar.com.gha.heroes.infrastructure.out.persistence.mapper.HeroeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class HeroeRepositoryImpl implements HeroeRepository {

    private final HeroeCrudRepository heroeCrudRepository;

    @Override
    public Iterable<Heroe> findAll() {
        return HeroeMapper.INSTANCE.toDomain(heroeCrudRepository.findAll());
    }

    @Override
    public Heroe save(Heroe heroe) {
        HeroeEntity entity = HeroeMapper.INSTANCE.map(heroe);
        HeroeEntity saved = heroeCrudRepository.save(entity);
        return HeroeMapper.INSTANCE.toDomain(saved);
    }

    @Override
    public Optional<Heroe> findByAlias(String alias) {
        return heroeCrudRepository.findByAlias(alias).map(HeroeMapper.INSTANCE::toDomain);
    }
}
