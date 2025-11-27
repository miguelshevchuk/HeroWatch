package ar.com.gha.auditms.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Audit {

    private Integer id;
    private String evento;
    private String mensaje;

}
