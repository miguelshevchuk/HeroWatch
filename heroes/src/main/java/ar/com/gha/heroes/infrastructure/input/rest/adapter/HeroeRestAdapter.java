package ar.com.gha.heroes.infrastructure.input.rest.adapter;

import ar.com.gha.heroes.application.usecase.ListHeroesUseCase;
import ar.com.gha.heroes.infrastructure.input.rest.mapper.HeroeApiMapper;
import ar.com.gha.heroes.infrastructure.input.rest.model.HeroeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heroes")
@RequiredArgsConstructor
public class HeroeRestAdapter {

    private final ListHeroesUseCase listHeroesUseCase;

    @GetMapping
    public ResponseEntity<Iterable<HeroeResponse>> findAll() {
        var heroes = listHeroesUseCase.execute();
        Iterable<HeroeResponse> body = HeroeApiMapper.INSTANCE.map(heroes);
        return ResponseEntity.ok(body);
    }
}
