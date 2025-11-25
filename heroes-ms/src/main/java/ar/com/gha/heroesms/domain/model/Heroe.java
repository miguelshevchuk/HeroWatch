package ar.com.gha.heroesms.domain.model;

import ar.com.gha.heroesms.domain.model.vo.Alias;
import ar.com.gha.heroesms.domain.model.vo.NivelEnergia;
import ar.com.gha.heroesms.domain.model.vo.NombreReal;
import ar.com.gha.heroesms.domain.model.vo.PoderNombre;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Heroe {

    private Integer id;
    private Alias alias;
    private NombreReal nombreReal;
    private HeroeEstado estado;
    private NivelEnergia nivelEnergia;
    private Iterable<Poder> poderes;

    public static Heroe create(String alias, String nombreReal, HeroeEstado estado, Integer nivelEnergia, List<String> poderes) {
        var voAlias = new Alias(alias);
        var voNombre = new NombreReal(nombreReal);
        var voEnergia = new NivelEnergia(nivelEnergia);
        var poderesDomain = poderes == null ? null : poderes.stream()
                .map(p -> new Poder(null, new PoderNombre(p)))
                .collect(Collectors.toList());
        return new Heroe(null, voAlias, voNombre, estado, voEnergia, poderesDomain);
    }
}
