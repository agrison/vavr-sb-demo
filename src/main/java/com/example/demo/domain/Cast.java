package com.example.demo.domain;

import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long castId;
    @ManyToOne
    @JoinColumn(name = "movieId")
    Movie movie;
    @ManyToOne
    @JoinColumn(name = "actorId")
    Actor actor;
    String role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Cast otherMovie) {
            return Option.of(castId)
                    .map(id -> id.equals(otherMovie.castId))
                    .getOrElse(false);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Option.of(castId).map(Object::hashCode)
                .getOrElse(44);
    }
}