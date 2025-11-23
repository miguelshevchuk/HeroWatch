package ar.com.gha.authserver.domain.repository;


import ar.com.gha.authserver.domain.model.User;

public interface UserRepository {

    User findByUsername(String username);

}
