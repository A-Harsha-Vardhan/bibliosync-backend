package com.library.management.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.library.management.entity.Book;
import com.library.management.service.BookService;

@CrossOrigin(origins = {"http://localhost:5173"})
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    public List<Book> getAllBooks() {
        // Matches: public List<Book> getAll() in Service
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        // Matches: public Book getBook(Long id)
        return service.getBook(id);
    }

    @GetMapping("/category/{category}")
    public List<Book> getByCategory(@PathVariable String category) {
        // Matches: public List<Book> getBooksByCategory(String category)
        return service.getBooksByCategory(category);
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String title) {
        // Matches: public List<Book> searchBooks(String title)
        return service.searchBooks(title);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        // Matches: public Book addBook(Book book)
        return service.addBook(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        // Matches: public Book updateBook(Long id, Book details)
        return service.updateBook(id, book);
    }

    @PutMapping("/{id}/issue")
    public Book issueBook(@PathVariable Long id, @RequestParam String borrowerName) {
        // Matches: public Book issueBook(Long id, String borrower)
        return service.issueBook(id, borrowerName);
    }

    @PutMapping("/{id}/return")
    public Book returnBook(@PathVariable Long id) {
        // Matches: public Book returnBook(Long id)
        return service.returnBook(id);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        // Matches: public String deleteBook(Long id)
        return service.deleteBook(id);
    }

    @PostMapping("/bulk")
    public List<Book> addMultipleBooks(@RequestBody List<Book> books) {
        // Matches: public List<Book> addBulkBooks(List<Book> books)
        return service.addBulkBooks(books);
    }
}