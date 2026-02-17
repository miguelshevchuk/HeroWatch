package ar.com.gha.authserver.infrastructure.input.rest.adapter;


import ar.com.gha.authserver.domain.model.Token;
import ar.com.gha.authserver.infrastructure.input.rest.model.LoginRequest;
import ar.com.gha.authserver.application.useCase.LoginUseCase;
import ar.com.gha.authserver.application.useCase.ValidateTokenUseCase;
import ar.com.gha.cryptoghastarter.infrastructure.security.crypto.AesGcmCryptoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthRestAdapter {

    private final LoginUseCase loginUseCase;
    private final ValidateTokenUseCase validateTokenUseCase;
    private final AesGcmCryptoService aesGcmCryptoService;

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

    @PostMapping("/crypt")
    public ResponseEntity<Token> cifrar(@RequestHeader String palabra) {
        log.info("cifrando palabra: {}", palabra);
        var palabraCifrada = aesGcmCryptoService.encryptToBase64(palabra);
        log.info("palabra cifrada: {}", palabraCifrada);
        var token = Token.builder().accessToken(aesGcmCryptoService.decryptFromBase64(palabraCifrada)).build();
        return ResponseEntity.ok(token);
    }

}
