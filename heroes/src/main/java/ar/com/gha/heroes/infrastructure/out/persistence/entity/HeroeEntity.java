package ar.com.gha.heroes.infrastructure.out.persistence.entity;

import ar.com.gha.heroes.domain.model.HeroeEstado;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "heroes")
public class HeroeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;
    private String alias;
    private String nombreReal;
    private HeroeEstado estadp;
    private Integer nivelEnergia;

    @OneToMany
    private List<PoderEntity> poderes;

}
