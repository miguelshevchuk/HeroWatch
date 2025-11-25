package ar.com.gha.heroesms.infrastructure.out.persistence.adapter.crud;

import ar.com.gha.heroesms.infrastructure.out.persistence.entity.HeroeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HeroeCrudRepository extends CrudRepository<HeroeEntity, Integer> {
    Optional<HeroeEntity> findByAlias(String alias);
}
