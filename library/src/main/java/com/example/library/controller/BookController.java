package com.example.library.controller;

import com.example.library.model.Book;

import java.time.LocalDateTime;
import java.util.List;

import com.example.library.repository.BookCollectionRepository;
import jakarta.validation.Valid;

import com.example.library.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/book")

public class BookController {

    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Book> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book Not Found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@Valid @RequestBody Book book) {
        book = new Book(
                null,
                book.title(),
                book.author(),
                book.genre(),
                book.status(),
                LocalDateTime.now(),
                null
        );
        repository.save(book);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Book book, @PathVariable Integer id) {
        Book existingBook = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found."));
        Book updatedBook = new Book(
                id,
                book.title(),
                book.author(),
                book.genre(),
                book.status(),
                existingBook.date_started(),
                LocalDateTime.now()
        );
        repository.save(updatedBook);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repository.deleteById(id);
    }

    @GetMapping("/filter/{keyword}")
    public List<Book> findByTitle(@PathVariable String keyword) {
        return repository.findAllByTitleContains(keyword);
    }
}
