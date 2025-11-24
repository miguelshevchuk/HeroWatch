package ar.com.gha.heroes.infrastructure.input.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoderResponse {
    private Integer id;
    private String poder;
}
