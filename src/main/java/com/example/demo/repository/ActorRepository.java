package com.example.demo.repository;

import com.example.demo.domain.Actor;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends PagingAndSortingRepository<Actor, Long> {
    Option<Actor> findByActorId(Long id);

    Seq<Actor> findAll();
}
