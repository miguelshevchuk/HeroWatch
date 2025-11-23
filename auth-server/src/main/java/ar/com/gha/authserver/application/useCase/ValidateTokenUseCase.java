package ar.com.gha.authserver.application.useCase;

import ar.com.gha.authserver.domain.model.Token;
import ar.com.gha.authserver.application.helper.JwtHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ValidateTokenUseCase {

    private final JwtHelper jwtHelper;

    public Token validate(String token) {

        jwtHelper.validateToken(token);

        return Token.builder().accessToken(token).build();
    }

}
