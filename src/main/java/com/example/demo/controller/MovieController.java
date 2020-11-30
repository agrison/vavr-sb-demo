package com.example.demo.controller;

import com.example.demo.domain.Cast;
import com.example.demo.domain.Movie;
import com.example.demo.domain.input.NewMovie;
import com.example.demo.service.MovieService;
import com.example.demo.validation.MovieValidator;
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
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public Seq<Movie> list() {
        return movieService.all();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> read(@PathVariable Long id) {
        return ResponseEntity.of(movieService.byId(id).toJavaOptional());
    }

    @GetMapping("/{id}/cast")
    public Seq<Cast> casts(@PathVariable Long id) {
        return movieService.castForMovie(id);
    }

    @PostMapping
    public ResponseEntity<Movie> post(@RequestBody NewMovie movie) {
        return Match(MovieValidator.validate(movie)).of(
                Case($Valid($()), movieService::create),
                Case($Invalid($()), x -> {
                    throw new ResponseStatusException(BAD_REQUEST, x.mkString());
                }))
                .map(e -> ResponseEntity.status(CREATED).body(e))
                .getOrElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR));
    }
}