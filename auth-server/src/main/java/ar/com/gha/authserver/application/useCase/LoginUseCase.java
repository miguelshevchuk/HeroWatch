package ar.com.gha.authserver.application.useCase;

import ar.com.gha.authserver.domain.model.Token;
import ar.com.gha.authserver.domain.repository.UserRepository;
import ar.com.gha.authserver.domain.service.UserValidatorService;
import ar.com.gha.authserver.application.helper.JwtHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@AllArgsConstructor
@Slf4j
public class LoginUseCase {

    private final JwtHelper jwtHelper;
    private final UserValidatorService userValidatorService;
    private final UserRepository userRepository;
    
    public Token login(String username, String password) {
        var user = userRepository.findByUsername(username);
        userValidatorService.validarUser(user);
        userValidatorService.validarPassword(user, password);

        return Token.builder().accessToken(jwtHelper.generateToken(user.getUsername())).build();
    }
    
}
