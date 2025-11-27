package ar.com.gha.heroesms.infrastructure.input.rest.adapter;

import ar.com.gha.heroesms.application.usecase.CreateHeroeUseCase;
import ar.com.gha.heroesms.application.usecase.ListHeroesUseCase;
import ar.com.gha.heroesms.application.usecase.UpdateHeroeUseCase;
import ar.com.gha.heroesms.domain.command.CreateHeroeCommand;
import ar.com.gha.heroesms.domain.command.UpdateHeroeCommand;
import ar.com.gha.heroesms.infrastructure.input.rest.mapper.CreateHeroeApiMapper;
import ar.com.gha.heroesms.infrastructure.input.rest.mapper.HeroeApiMapper;
import ar.com.gha.heroesms.infrastructure.input.rest.model.CrearHeroeRequest;
import ar.com.gha.heroesms.infrastructure.input.rest.model.EditarHeroeRequest;
import ar.com.gha.heroesms.infrastructure.input.rest.model.HeroeResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class HeroeRestAdapter {

    private final ListHeroesUseCase listHeroesUseCase;
    private final CreateHeroeUseCase createHeroeUseCase;
    private final UpdateHeroeUseCase updateHeroeUseCase;

    @GetMapping("/heroes")
    public ResponseEntity<Iterable<HeroeResponse>> findAll() {
        var heroes = listHeroesUseCase.execute();
        Iterable<HeroeResponse> body = HeroeApiMapper.INSTANCE.toHeroeResponse(heroes);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/heroe")
    public ResponseEntity<HeroeResponse> create(@Valid @RequestBody CrearHeroeRequest request) {
        CreateHeroeCommand command = CreateHeroeApiMapper.INSTANCE.toCommand(request);
        var created = createHeroeUseCase.execute(command);
        var body = HeroeApiMapper.INSTANCE.toHeroeResponse(created);
        return ResponseEntity.ok(body);
    }

    @PutMapping("/heroe")
    public ResponseEntity<HeroeResponse> update(@Valid @RequestBody EditarHeroeRequest request) {
        UpdateHeroeCommand command = CreateHeroeApiMapper.INSTANCE.toCommand(request);
        var updated = updateHeroeUseCase.execute(command);
        var body = HeroeApiMapper.INSTANCE.toHeroeResponse(updated);

        return ResponseEntity.ok(body);
    }
}
