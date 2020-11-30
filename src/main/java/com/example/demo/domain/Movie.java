package com.example.demo.domain;

import io.vavr.control.Option;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@With
@Entity
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long movieId;
    String title;
    LocalDate releaseDate;
    String synopsis;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Movie otherMovie) {
            return Option.of(movieId)
                    .map(id -> id.equals(otherMovie.movieId))
                    .getOrElse(false);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Option.of(movieId).map(Object::hashCode)
                .getOrElse(43);
    }
}