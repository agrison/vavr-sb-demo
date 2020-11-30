package com.example.demo.service;

import com.example.demo.domain.Actor;
import com.example.demo.domain.Cast;
import com.example.demo.repository.ActorRepository;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActorService {
    private final ActorRepository repository;
    private final CastService castService;

    public Seq<Actor> all() {
        return repository.findAll();
    }

    public Option<Actor> byId(Long id) {
        return repository.findByActorId(id);
    }

    public Seq<Cast> castsForActor(Long actorId) {
        return castService.castForActor(actorId);
    }

    public Try<Actor> create(Actor actor) {
        return Try.of(() -> repository.save(actor.withActorId(null)));
    }
}
