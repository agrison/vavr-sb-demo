package com.example.demo.repository;

import com.example.demo.domain.Actor;
import com.example.demo.domain.Cast;
import com.example.demo.domain.Movie;
import io.vavr.collection.Seq;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CastRepository extends PagingAndSortingRepository<Cast, Long> {
    Seq<Cast> findAllByActor(Actor actor);

    Seq<Cast> findAllByMovie(Movie movie);
}
