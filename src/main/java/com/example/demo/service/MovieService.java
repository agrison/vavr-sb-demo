package com.example.demo.service;

import com.example.demo.domain.Cast;
import com.example.demo.domain.Movie;
import com.example.demo.repository.MovieRepository;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository repository;
    private final com.example.demo.service.CastService castService;

    public Seq<Movie> all() {
        return repository.findAll();
    }

    public Option<Movie> byId(Long id) {
        return repository.findByMovieId(id);
    }

    public Seq<Cast> castForMovie(Long id) {
        return castService.castForMovie(id);
    }

    public Try<Movie> create(Movie movie) {
        return Try.of(() -> repository.save(movie.withMovieId(null)));
    }

    public Try<Cast> addActorToMovieCast(Long movieId, Long actorId, String role) {
        return repository.findById(movieId)
                .map(movie -> castService.addActorToMovie(movie, actorId, role))
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }
}
