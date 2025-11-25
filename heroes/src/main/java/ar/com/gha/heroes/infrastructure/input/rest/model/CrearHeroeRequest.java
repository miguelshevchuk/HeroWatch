package ar.com.gha.heroes.infrastructure.input.rest.model;

import ar.com.gha.heroes.domain.model.HeroeEstado;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class CrearHeroeRequest {

    @NotBlank
    private String alias;

    @NotBlank
    private String nombreReal;

    @NotNull
    private HeroeEstado estado;

    @NotNull
    @Min(0)
    @Max(100)
    private Integer nivelEnergia;

    private List<@NotBlank String> poderes;
}
