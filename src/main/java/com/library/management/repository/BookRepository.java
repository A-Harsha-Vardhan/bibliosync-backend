package com.library.management.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.library.management.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Search by title
    List<Book> findByTitleContainingIgnoreCase(String title);
    
    // Filter by category
    List<Book> findByCategory(String category);
}