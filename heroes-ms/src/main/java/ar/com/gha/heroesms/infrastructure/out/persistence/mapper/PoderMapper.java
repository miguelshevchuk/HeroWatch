package ar.com.gha.heroesms.infrastructure.out.persistence.mapper;


import ar.com.gha.heroesms.domain.model.Poder;
import ar.com.gha.heroesms.domain.model.vo.PoderNombre;
import ar.com.gha.heroesms.infrastructure.out.persistence.entity.PoderEntity;
import ar.com.gha.mapperghastarter.infrastructure.GhaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PoderMapper extends GhaMapper<Poder, PoderEntity> {

    PoderMapper INSTANCE = Mappers.getMapper(PoderMapper.class);

    // MapStruct helpers for VO <-> primitive
    default String map(PoderNombre v) { return v == null ? null : v.value(); }
    default PoderNombre map(String v) { return v == null ? null : new PoderNombre(v); }
}
