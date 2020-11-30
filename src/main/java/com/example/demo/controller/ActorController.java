package com.example.demo.controller;

import com.example.demo.domain.Actor;
import com.example.demo.domain.Cast;
import com.example.demo.domain.input.NewActor;
import com.example.demo.service.ActorService;
import com.example.demo.validation.ActorValidator;
import io.vavr.collection.Seq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static io.vavr.API.*;
import static io.vavr.Patterns.$Invalid;
import static io.vavr.Patterns.$Valid;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/actors")
public class ActorController {
    private final ActorService actorService;

    @GetMapping
    public Seq<Actor> list() {
        return actorService.all();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> read(@PathVariable Long id) {
        return ResponseEntity.of(actorService.byId(id).toJavaOptional());
    }

    @GetMapping("/{id}/cast")
    public Seq<Cast> casts(@PathVariable Long id) {
        return actorService.castsForActor(id);
    }

    @PostMapping
    public ResponseEntity<Actor> post(@RequestBody NewActor actor) {
        return Match(ActorValidator.validate(actor)).of(
                Case($Valid($()), actorService::create),
                Case($Invalid($()), x -> {
                    throw new ResponseStatusException(BAD_REQUEST, x.mkString());
                }))
                .map(e -> ResponseEntity.status(CREATED).body(e))
                .getOrElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR));
    }
}