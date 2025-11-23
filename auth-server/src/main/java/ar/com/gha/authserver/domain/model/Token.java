package ar.com.gha.authserver.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {

    private String accessToken;
    private String refreshToken;
    private Long expiresIn;

}
