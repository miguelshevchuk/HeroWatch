package ar.com.gha.heroes.infrastructure.input.rest.mapper;

import ar.com.gha.heroes.domain.model.Heroe;
import ar.com.gha.heroes.domain.model.vo.Alias;
import ar.com.gha.heroes.domain.model.vo.NombreReal;
import ar.com.gha.heroes.domain.model.vo.NivelEnergia;
import ar.com.gha.heroes.infrastructure.input.rest.model.HeroeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PoderApiMapper.class})
public interface HeroeApiMapper{
    HeroeApiMapper INSTANCE = Mappers.getMapper(HeroeApiMapper.class);

    Iterable<HeroeResponse> toHeroeResponse(Iterable<Heroe> heroes);
    HeroeResponse toHeroeResponse(Heroe heroe);

    // VO -> primitive helpers for API layer
    default String map(Alias v) { return v == null ? null : v.value(); }
    default String map(NombreReal v) { return v == null ? null : v.value(); }
    default Integer map(NivelEnergia v) { return v == null ? null : v.value(); }
}
