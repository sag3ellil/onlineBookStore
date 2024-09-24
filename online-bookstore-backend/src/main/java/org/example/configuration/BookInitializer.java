package org.example.configuration;



import org.example.entity.Book;
import org.example.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class BookInitializer {

    private final JdbcTemplate jdbcTemplate;

    public BookInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    CommandLineRunner initDatabase(BookRepository bookRepository) {
        return args -> {
            // Disable referential integrity
            jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE");

            // Delete all records from the Books table
            jdbcTemplate.execute("TRUNCATE TABLE Books");

            // Enable referential integrity
            jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE");

            // Now you can safely insert new book records
            bookRepository.save(new Book(10, "The Great Gatsby", "F. Scott Fitzgerald", 10.99));
            bookRepository.save(new Book(20, "1984", "George Orwell", 8.99));
            bookRepository.save(new Book(30, "To Kill a Mockingbird", "Harper Lee", 12.99));
            bookRepository.save(new Book(40, "Pride and Prejudice", "Jane Austen", 9.99));
            bookRepository.save(new Book(50, "The Catcher in the Rye", "J.D. Salinger", 11.99));
        };
    }
}
