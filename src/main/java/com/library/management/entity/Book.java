package com.library.management.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String author;
    private String category;
    
    @Column(length = 500) 
    private String imageUrl = "";
    
    private int totalCopies;
    private int availableCopies;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "book_history", 
        joinColumns = @JoinColumn(name = "book_id")
    )
    @OrderColumn(name = "borrower_history_order")
    private List<LogEntry> borrowerHistory = new ArrayList<>();

    public Book() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public int getTotalCopies() { return totalCopies; }
    public void setTotalCopies(int totalCopies) { this.totalCopies = totalCopies; }
    public int getAvailableCopies() { return availableCopies; }
    public void setAvailableCopies(int availableCopies) { this.availableCopies = availableCopies; }
    public List<LogEntry> getBorrowerHistory() { return borrowerHistory; }
    public void setBorrowerHistory(List<LogEntry> borrowerHistory) { this.borrowerHistory = borrowerHistory; }
}