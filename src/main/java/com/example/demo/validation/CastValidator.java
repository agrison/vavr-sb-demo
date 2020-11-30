package com.example.demo.validation;

import com.example.demo.domain.input.NewCast;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.apache.commons.lang3.StringUtils;

import static io.vavr.control.Validation.invalid;
import static io.vavr.control.Validation.valid;

public class CastValidator {
    static Validation<Seq<String>, NewCast> validate(NewCast cast) {
        return Validation.combine(
                validActorId(cast.actorId()),
                roleCannotBeBlank(cast.role()))
                .ap((id, role) -> cast);
    }

    static Validation<String, Long> validActorId(Long id) {
        return id == null || id.equals(0L) ? invalid("Actor ID must be valid") : valid(id);
    }

    static Validation<String, String> roleCannotBeBlank(String role) {
        return StringUtils.isBlank(role) ? invalid("Last role cannot be blank") : valid(role);
    }
}
