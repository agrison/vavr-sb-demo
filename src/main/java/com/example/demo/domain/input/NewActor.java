package com.example.demo.domain.input;

import java.time.LocalDate;

public record NewActor(String firstName, String lastName, LocalDate dateOfBirth) {
}
