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
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long actorId;
    String firstName;
    String lastName;
    LocalDate dateOfBirth;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Actor otherActor) {
            return Option.of(actorId)
                    .map(id -> id.equals(otherActor.actorId))
                    .getOrElse(false);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Option.of(actorId).map(Object::hashCode)
                .getOrElse(42);
    }
}