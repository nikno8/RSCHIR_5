package com.mirea.rschirpractice5.controller;

import com.google.gson.Gson;
import com.mirea.rschirpractice5.entity.Book;
import com.mirea.rschirpractice5.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final Gson gson;
    private final BookService bookService;


    @GetMapping
    public ResponseEntity<String> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        // Сериализуем список книг в JSON
        String json = gson.toJson(books);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.findBookById(id);
        if (book.isPresent()) {
            // Сериализуем объект Book в JSON
            String json = gson.toJson(book.get());
            return new ResponseEntity<>(json, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createBook(@RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        // Сериализуем созданную книгу в JSON
        String json = gson.toJson(createdBook);
        return new ResponseEntity<>(json, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Optional<Book> existingBook = bookService.findBookById(id);
        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            book.setAuthor(updatedBook.getAuthor());
            book.setSellerId(updatedBook.getSellerId());
            book.setProductType(updatedBook.getProductType());
            book.setPrice(updatedBook.getPrice());
            book.setTitle(updatedBook.getTitle());
            Book updated = bookService.createBook(book);
            // Сериализуем обновленную книгу в JSON
            String json = gson.toJson(updated);
            return new ResponseEntity<>(json, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        Optional<Book> book = bookService.findBookById(id);
        if (book.isPresent()) {
            bookService.deleteBookById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
