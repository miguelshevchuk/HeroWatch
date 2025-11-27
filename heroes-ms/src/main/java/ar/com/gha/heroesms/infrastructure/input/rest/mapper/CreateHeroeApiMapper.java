package ar.com.gha.heroesms.infrastructure.input.rest.mapper;


import ar.com.gha.heroesms.domain.command.CreateHeroeCommand;
import ar.com.gha.heroesms.domain.command.UpdateHeroeCommand;
import ar.com.gha.heroesms.infrastructure.input.rest.model.CrearHeroeRequest;
import ar.com.gha.heroesms.infrastructure.input.rest.model.EditarHeroeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateHeroeApiMapper {
    CreateHeroeApiMapper INSTANCE = Mappers.getMapper(CreateHeroeApiMapper.class);

    CreateHeroeCommand toCommand(CrearHeroeRequest request);
    UpdateHeroeCommand toCommand(EditarHeroeRequest heroe);
}
