package ar.com.gha.heroes.infrastructure.out.persistence.adapter.crud;

import ar.com.gha.heroes.infrastructure.out.persistence.entity.HeroeEntity;
import org.springframework.data.repository.CrudRepository;

public interface HeroeCrudRepository extends CrudRepository<HeroeEntity, Integer> {
}
