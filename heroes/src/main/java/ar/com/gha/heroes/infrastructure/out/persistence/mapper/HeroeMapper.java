package ar.com.gha.heroes.infrastructure.out.persistence.mapper;
import ar.com.gha.heroes.domain.model.Heroe;
import ar.com.gha.heroes.domain.model.vo.Alias;
import ar.com.gha.heroes.domain.model.vo.NombreReal;
import ar.com.gha.heroes.domain.model.vo.NivelEnergia;
import ar.com.gha.heroes.infrastructure.out.persistence.entity.HeroeEntity;
import ar.com.gha.mapperghastarter.infrastructure.GhaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PoderMapper.class})
public interface HeroeMapper extends GhaMapper<Heroe, HeroeEntity> {

    HeroeMapper INSTANCE = Mappers.getMapper(HeroeMapper.class);

    default String map(Alias v) { return v == null ? null : v.value(); }
    default Alias mapAlias(String v) { return v == null ? null : new Alias(v); }

    default String map(NombreReal v) { return v == null ? null : v.value(); }
    default NombreReal mapNombre(String v) { return v == null ? null : new NombreReal(v); }

    default Integer map(NivelEnergia v) { return v == null ? null : v.value(); }
    default NivelEnergia mapEnergia(Integer v) { return v == null ? null : new NivelEnergia(v); }
}
