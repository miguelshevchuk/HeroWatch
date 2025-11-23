package ar.com.gha.authserver.infrastructure.input.rest.adapter;


import ar.com.gha.authserver.domain.model.Token;
import ar.com.gha.authserver.infrastructure.input.rest.model.LoginRequest;
import ar.com.gha.authserver.application.useCase.LoginUseCase;
import ar.com.gha.authserver.application.useCase.ValidateTokenUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthRestAdapter {

    private final LoginUseCase loginUseCase;
    private final ValidateTokenUseCase validateTokenUseCase;

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody LoginRequest loginRequest) {
        var token = loginUseCase.login(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/jwt")
    public ResponseEntity<Token> validarToken(@RequestHeader String accessToken) {
        var token = validateTokenUseCase.validate(accessToken);
        return ResponseEntity.ok(token);
    }

}
