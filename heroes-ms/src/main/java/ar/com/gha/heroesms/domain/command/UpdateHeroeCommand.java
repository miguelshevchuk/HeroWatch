package ar.com.gha.heroesms.domain.command;


import ar.com.gha.heroesms.domain.model.HeroeEstado;

import java.util.List;

public record UpdateHeroeCommand(
        Integer id,
        String alias,
        String nombreReal,
        HeroeEstado estado,
        Integer nivelEnergia,
        List<String> poderes
) {}
