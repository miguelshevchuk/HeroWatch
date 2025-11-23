package ar.com.gha.authserver.infrastructure.output.persistence.adapter;

import ar.com.gha.authserver.domain.model.User;
import ar.com.gha.authserver.domain.repository.UserRepository;
import ar.com.gha.authserver.infrastructure.output.persistence.mapper.UserMapper;
import ar.com.gha.authserver.infrastructure.output.persistence.repository.UserCRUDRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserAdapter implements UserRepository {

    private final UserCRUDRepository userCRUDRepository;

    @Override
    public User findByUsername(String username) {
        return UserMapper.INSTANCE.toUser(userCRUDRepository.findByUsername(username));
    }
}
