package com.example.demo.repository;

import com.example.demo.domain.Movie;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {
    Option<Movie> findByMovieId(Long movieId);

    Seq<Movie> findAll();
}
