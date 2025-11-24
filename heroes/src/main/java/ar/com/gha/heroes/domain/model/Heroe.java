package ar.com.gha.heroes.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Heroe {

    private Integer id;
    private String alias;
    private String nombreReal;
    private HeroeEstado estadp;
    private Integer nivelEnergia;
    private Iterable<Poder> poderes;

}
