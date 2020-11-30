package com.example.demo.domain.input;

import java.time.LocalDate;

public record NewMovie(String title, String synopsis, LocalDate releaseDate) { }