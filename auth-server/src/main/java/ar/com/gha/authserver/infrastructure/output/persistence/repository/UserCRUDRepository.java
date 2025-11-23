package ar.com.gha.authserver.infrastructure.output.persistence.repository;

import ar.com.gha.authserver.infrastructure.output.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCRUDRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByUsername(String username);

}
