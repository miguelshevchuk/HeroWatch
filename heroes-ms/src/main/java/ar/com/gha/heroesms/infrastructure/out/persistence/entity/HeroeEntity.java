package ar.com.gha.heroesms.infrastructure.out.persistence.entity;


import ar.com.gha.heroesms.domain.model.HeroeEstado;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "heroes")
public class HeroeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String alias;

    @Column(name = "nombre_real")
    private String nombreReal;

    @Enumerated(EnumType.STRING)
    private HeroeEstado estado;

    @Column(name = "nivel_energia")
    private Integer nivelEnergia;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "heroe_id")
    private List<PoderEntity> poderes;

}
