package ar.com.gha.heroes.infrastructure.out.persistence.mapper;
import ar.com.gha.heroes.domain.model.Heroe;
import ar.com.gha.heroes.infrastructure.out.persistence.entity.HeroeEntity;
import ar.com.gha.mapperghastarter.infrastructure.GhaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface HeroeMapper extends GhaMapper<Heroe, HeroeEntity> {

    HeroeMapper INSTANCE = Mappers.getMapper(HeroeMapper.class);


}
