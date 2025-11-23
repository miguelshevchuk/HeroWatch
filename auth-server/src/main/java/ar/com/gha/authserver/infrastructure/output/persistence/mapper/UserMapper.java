package ar.com.gha.authserver.infrastructure.output.persistence.mapper;


import ar.com.gha.authserver.domain.model.User;
import ar.com.gha.authserver.infrastructure.output.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    public static UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity toEntity(User user);
    User toUser(UserEntity userEntity);

}
