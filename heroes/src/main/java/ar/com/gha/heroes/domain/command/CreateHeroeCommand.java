package ar.com.gha.heroes.domain.command;

import ar.com.gha.heroes.domain.model.HeroeEstado;

import java.util.List;

public record CreateHeroeCommand(
        String alias,
        String nombreReal,
        HeroeEstado estado,
        Integer nivelEnergia,
        List<String> poderes
) {}
