package ar.com.gha.heroes.domain.model;

import ar.com.gha.heroes.domain.model.vo.PoderNombre;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Poder {

    private Integer id;
    private PoderNombre poder;

}
