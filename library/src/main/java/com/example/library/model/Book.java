package com.example.library.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;


public record Book(
        @Id
        Integer id,
        String title,
        String author,
        Genre genre,
        Status status,
        LocalDateTime date_started,
        LocalDateTime date_read
) {
}
