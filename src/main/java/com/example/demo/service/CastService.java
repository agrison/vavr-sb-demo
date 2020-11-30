package com.example.demo.service;

import com.example.demo.domain.Cast;
import com.example.demo.domain.Movie;
import com.example.demo.repository.ActorRepository;
import com.example.demo.repository.CastRepository;
import com.example.demo.repository.MovieRepository;
import io.vavr.collection.Seq;
import io.vavr.collection.Vector;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CastService {
    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;
    private final CastRepository castRepository;

    public Seq<Cast> castForActor(Long actorId) {
        return castFor(actorId, actorRepository, castRepository::findAllByActor);
    }

    public Seq<Cast> castForMovie(Long movieId) {
        return castFor(movieId, movieRepository, castRepository::findAllByMovie);
    }

    private <T> Seq<Cast> castFor(Long entityId, CrudRepository<T, Long> repository, Function<T, Seq<Cast>> mapper) {
        return repository.findById(entityId)
                .map(mapper)
                .orElse(Vector.empty());
    }

    public Try<Cast> addActorToMovie(Movie movie, Long actorId, String role) {
        return Try.of(() -> actorRepository.findById(actorId)
                .map(a -> castRepository.save(new Cast(null, movie, a, role)))
                .orElseThrow(() -> new RuntimeException("Actor not found")));
    }
}
