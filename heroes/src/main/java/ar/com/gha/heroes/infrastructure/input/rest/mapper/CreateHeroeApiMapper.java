package ar.com.gha.heroes.infrastructure.input.rest.mapper;

import ar.com.gha.heroes.domain.command.CreateHeroeCommand;
import ar.com.gha.heroes.infrastructure.input.rest.model.CrearHeroeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateHeroeApiMapper {
    CreateHeroeApiMapper INSTANCE = Mappers.getMapper(CreateHeroeApiMapper.class);

    CreateHeroeCommand toCommand(CrearHeroeRequest request);
}
