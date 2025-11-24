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

    @Column(name = "nombre_real")
    private String nombreReal;

    @Enumerated(EnumType.STRING)
    private HeroeEstado estado;

    @Column(name = "nivel_energia")
    private Integer nivelEnergia;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "heroe_id")
    private List<PoderEntity> poderes;

}
