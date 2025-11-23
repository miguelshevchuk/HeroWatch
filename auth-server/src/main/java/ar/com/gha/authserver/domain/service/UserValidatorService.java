package ar.com.gha.authserver.domain.service;

import ar.com.gha.authserver.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
@Slf4j
public class UserValidatorService {

    public static final String DATOS_INVALIDOS = "Datos invalidos";

    public void validarPassword(User user, String password) {
        if (!user.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, DATOS_INVALIDOS);
        }
    }

    public void validarUser(User user) {
        if(Objects.isNull(user)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, DATOS_INVALIDOS);
        }
    }

}
