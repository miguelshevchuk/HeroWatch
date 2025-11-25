package ar.com.gha.heroesms.infrastructure.input.rest.model;

import ar.com.gha.heroesms.domain.model.HeroeEstado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeroeResponse {
    private Integer id;
    private String alias;
    private String nombreReal;
    private HeroeEstado estado;
    private Integer nivelEnergia;
    private List<PoderResponse> poderes;
}
