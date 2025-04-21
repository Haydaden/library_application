package com.example.library.repository;

import java.util.List;
import com.example.library.model.Book;
import org.springframework.data.repository.ListCrudRepository;


public interface BookRepository extends ListCrudRepository<Book, Integer> {
    List<Book> findAllByTitleContains(String keyword);
}