package ar.com.gha.mapperghastarter.infrastructure;

import org.mapstruct.Mapper;

@Mapper
public interface GhaMapper<Domain, Entity> {

    Domain toDomain(Entity entity);
    Entity toEntity(Domain domain);
    Iterable<Domain> toDomain(Iterable<Entity> entities);
    Iterable<Entity> toEntity(Iterable<Domain> domains);

}
