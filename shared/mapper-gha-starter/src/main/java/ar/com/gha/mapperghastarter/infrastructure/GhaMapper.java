package ar.com.gha.mapperghastarter.infrastructure;

import org.mapstruct.Mapper;

@Mapper
public interface GhaMapper<Domain, Object> {

    Domain toDomain(Object entity);
    Object map(Domain domain);
    Iterable<Domain> toDomain(Iterable<Object> entities);
    Iterable<Object> map(Iterable<Domain> domains);

}
