package com.example.library.repository;

import com.example.library.model.Book;
import com.example.library.model.Genre;
import com.example.library.model.Status;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

//H2 database left here for testing as needed.

@Repository
public class BookCollectionRepository {

    private final List<Book> books = new ArrayList<>();

    public BookCollectionRepository() {
    }

    public List<Book> findAll() {
        return books;
    }

    public Optional<Book> findById(Integer id) {
        return books.stream()
                .filter(b -> b.id().equals(id))
                .findFirst();
    }

    public List<Book> findByTitle(String keyword) {
        return books.stream()
                .filter(b -> b.title().toLowerCase().contains(keyword.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    public void save(Book book) {
        //add code for replacing existing id
        books.add(book);
    }

    public boolean existsById(Integer id) {
        return books.stream()
                .filter(b -> b.id().equals(id))
                .count() == 1;
    }

    public void delete(Integer id) {
        books.removeIf(b -> b.id().equals(id));
    }
}