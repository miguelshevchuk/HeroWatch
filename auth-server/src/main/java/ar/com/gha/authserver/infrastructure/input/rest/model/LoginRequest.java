package ar.com.gha.authserver.infrastructure.input.rest.model;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;

}
