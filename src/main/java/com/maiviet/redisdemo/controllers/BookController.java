package com.maiviet.redisdemo.controllers;

import com.maiviet.redisdemo.entities.BookEntity;
import com.maiviet.redisdemo.exceptions.ResourceNotFoundException;
import com.maiviet.redisdemo.repositories.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public String greeting() {
        return "Hello World!";
    }

    @PostMapping("/books")
    public BookEntity addBook(@Valid @RequestBody BookEntity bookEntity) {
        return bookRepository.save(bookEntity);
    }

    @GetMapping("/books")
    @Cacheable("books")
    public List<BookEntity> getAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    @Cacheable(value = "books", key="#id")
    public BookEntity getBook(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found book with id "+ id));
    }

    @PutMapping("/books/{id}")
    @CachePut(value="books", key="#id", unless="#result==null")
    public BookEntity updateBook(@PathVariable Long id, @Valid @RequestBody BookEntity bookRequest) {
        return bookRepository.findById(id)
                .map(bookEntity -> {
                    bookEntity.setTitle(bookRequest.getTitle());
                    return bookRepository.save(bookEntity);

                }).orElseThrow(() -> new ResourceNotFoundException("Not found book with id " + id));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(bookEntity -> {
                    bookRepository.delete(bookEntity);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Not found book with id " + id));
    }
}
