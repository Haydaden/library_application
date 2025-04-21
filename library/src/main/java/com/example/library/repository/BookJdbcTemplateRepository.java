package com.example.library.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.example.library.model.Book;
import com.example.library.model.Genre;
import com.example.library.model.Status;
import org.springframework.cglib.core.Local;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class BookJdbcTemplateRepository {

    private final JdbcTemplate jdbcTemplate;

    public BookJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Book(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                Genre.valueOf(rs.getString("genre")),
                Status.valueOf(rs.getString("status")),
                        rs.getObject("date_started", LocalDateTime.class),
                        rs.getObject("date_read",LocalDateTime.class));
    }

    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM book";
        return jdbcTemplate.query(sql, BookJdbcTemplateRepository::mapRow);
    }

    public void createBook(String title, String author, Status status, Genre genre) {
        String sql = "INSERT INTO book (title, author, status, genre, date_created, date_read) VALUES (?, ?, ?, ?, ?, ?)";
        LocalDateTime now = LocalDateTime.now();
        jdbcTemplate.update(sql, title, author, status.name(), genre.name(), now, null);
    }

    public void updateBook(int id, String title, String author, Status status, Genre genre) {
        String sql = "UPDATE book SET title=?, author=?, status=?, genre=?, date_updated=NOW() WHERE id=?";
        jdbcTemplate.update(sql, title, author, status.name(), genre.name(), id);
    }

    public void deleteBook(int id) {
        String sql = "DELETE FROM book WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    public Book getBook(int id) {
        String sql = "SELECT * FROM book WHERE id=?";
        Book book = jdbcTemplate.queryForObject(sql, new Object[]{id}, BookJdbcTemplateRepository::mapRow);
        return book;
    }
}