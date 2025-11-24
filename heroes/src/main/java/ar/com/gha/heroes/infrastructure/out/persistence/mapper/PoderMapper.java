package ar.com.gha.heroes.infrastructure.out.persistence.mapper;

import ar.com.gha.heroes.domain.model.Poder;
import ar.com.gha.heroes.infrastructure.out.persistence.entity.PoderEntity;
import ar.com.gha.mapperghastarter.infrastructure.GhaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PoderMapper extends GhaMapper<Poder, PoderEntity> {

    PoderMapper INSTANCE = Mappers.getMapper(PoderMapper.class);

}
