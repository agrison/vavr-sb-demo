package com.example.demo.validation;

import com.example.demo.domain.Movie;
import com.example.demo.domain.input.NewMovie;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

import static io.vavr.control.Validation.invalid;
import static io.vavr.control.Validation.valid;

public class MovieValidator {
    public static Validation<Seq<String>, Movie> validate(NewMovie movie) {
        return Validation.combine(
                titleCannotBeBlank(movie.title()),
                synopsisMaxLength(movie.synopsis()),
                dateInThePastOrPresent(movie.releaseDate()))
                .ap((title, synopsis, date) -> new Movie(null, title, date, synopsis));
    }

    static Validation<String, String> titleCannotBeBlank(String name) {
        return StringUtils.isBlank(name) ? invalid("Movie name cannot be blank") : valid(name);
    }

    static Validation<String, String> synopsisMaxLength(String synopsis) {
        return StringUtils.length(synopsis) > 100 ? invalid("Synopsis must be 100 chars max") : valid(synopsis);
    }

    static Validation<String, LocalDate> dateInThePastOrPresent(LocalDate date) {
        return (date.isAfter(LocalDate.now())) ? invalid("Release date must be in the past") : valid(date);
    }
}
