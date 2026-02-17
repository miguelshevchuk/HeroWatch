package ar.com.gha.authserver.domain.service;

import ar.com.gha.authserver.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class UserValidatorService {

    public static final String DATOS_INVALIDOS = "Datos invalidos";
    public final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void validarPassword(User user, String password) {
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, DATOS_INVALIDOS);
        }
    }

    public void validarUser(User user) {
        if(Objects.isNull(user)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, DATOS_INVALIDOS);
        }
    }

}
