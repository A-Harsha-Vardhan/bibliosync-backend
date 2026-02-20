package com.library.management.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.library.management.entity.Book;
import com.library.management.entity.LogEntry;
import com.library.management.repository.BookRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository repo;

    public List<Book> getAll() { 
        return repo.findAll(); 
    }

    public Book getBook(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public List<Book> getBooksByCategory(String category) {
        return repo.findByCategory(category);
    }

    public List<Book> searchBooks(String title) {
        return repo.findByTitleContainingIgnoreCase(title);
    }

    @Transactional
    public Book addBook(Book book) {
        book.setAvailableCopies(book.getTotalCopies());
        return repo.save(book);
    }

    @Transactional
    public List<Book> addBulkBooks(List<Book> books) {
        for (Book book : books) {
            book.setAvailableCopies(book.getTotalCopies());
        }
        return repo.saveAll(books);
    }

    @Transactional
    public Book updateBook(Long id, Book details) {
        Book book = getBook(id);
        book.setTitle(details.getTitle());
        book.setAuthor(details.getAuthor());
        book.setCategory(details.getCategory());
        book.setTotalCopies(details.getTotalCopies());
        book.setAvailableCopies(details.getTotalCopies()); 
        return repo.save(book);
    }

    @Transactional
    public String deleteBook(Long id) {
        repo.deleteById(id);
        return "Book removed successfully";
    }

    @Transactional
    public Book issueBook(Long id, String borrower) {
        Book book = getBook(id);
        
        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Out of stock");
        }

        // Create new LogEntry object
        LogEntry entry = new LogEntry();
        entry.setBorrowerName(borrower);
        entry.setIssueDate(LocalDate.now()); // Set today's date
        entry.setFineAmount(0.0);

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        
        // Add to history (index 0 for most recent)
        book.getBorrowerHistory().add(0, entry);
        
        return repo.save(book);
    }

    @Transactional
    public Book returnBook(Long id) {
        Book book = getBook(id);
        
        if (book.getAvailableCopies() >= book.getTotalCopies()) {
            throw new RuntimeException("All copies are already in the library");
        }

        // Find the most recent log (index 0) to set the return date
        if (!book.getBorrowerHistory().isEmpty()) {
            LogEntry latestLog = book.getBorrowerHistory().get(0);
            latestLog.setReturnDate(LocalDate.now());

            // --- FINE CALCULATION LOGIC ---
            // Example: 14-day limit, ₹5 fine per extra day
            long daysBetween = ChronoUnit.DAYS.between(latestLog.getIssueDate(), latestLog.getReturnDate());
            if (daysBetween > 14) {
                double fine = (daysBetween - 14) * 5.0;
                latestLog.setFineAmount(fine);
            }
        }

        book.setAvailableCopies(book.getAvailableCopies() + 1);
        return repo.save(book);
    }
}