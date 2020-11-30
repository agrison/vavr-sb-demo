package com.example.demo.validation;

import com.example.demo.domain.Actor;
import com.example.demo.domain.input.NewActor;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

import static io.vavr.control.Validation.invalid;
import static io.vavr.control.Validation.valid;

public class ActorValidator {
    public static Validation<Seq<String>, Actor> validate(NewActor actor) {
        return Validation.combine(
                firstNameCannotBeBlank(actor.firstName()),
                lastNameCannotBeBlank(actor.lastName()),
                dateInThePast(actor.dateOfBirth()))
                .ap((firstName, lastName, date) -> new Actor(null, firstName, lastName, date));
    }

    static Validation<String, String> firstNameCannotBeBlank(String name) {
        return StringUtils.isBlank(name) ? invalid("First name cannot be blank") : valid(name);
    }

    static Validation<String, String> lastNameCannotBeBlank(String name) {
        return StringUtils.isBlank(name) ? invalid("Last name cannot be blank") : valid(name);
    }

    static Validation<String, LocalDate> dateInThePast(LocalDate date) {
        return !date.isBefore(LocalDate.now()) ? invalid("Date of birth must be in the past") : valid(date);
    }
}
