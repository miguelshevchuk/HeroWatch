package ar.com.gha.heroes.infrastructure.input.rest.mapper;

import ar.com.gha.heroes.domain.model.Heroe;
import ar.com.gha.heroes.infrastructure.input.rest.model.HeroeResponse;
import ar.com.gha.mapperghastarter.infrastructure.GhaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {PoderApiMapper.class})
public interface HeroeApiMapper extends GhaMapper<Heroe, HeroeResponse> {
    HeroeApiMapper INSTANCE = Mappers.getMapper(HeroeApiMapper.class);

}
