package ar.com.gha.heroes.infrastructure.input.rest.mapper;

import ar.com.gha.heroes.domain.model.Poder;
import ar.com.gha.heroes.infrastructure.input.rest.model.PoderResponse;
import ar.com.gha.mapperghastarter.infrastructure.GhaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PoderApiMapper  extends GhaMapper<Poder, PoderResponse> {
    PoderApiMapper INSTANCE = Mappers.getMapper(PoderApiMapper.class);

}
