package com.library.management.entity;

import java.time.*;
import jakarta.persistence.*;

@Embeddable
public class LogEntry {
    
    @Column(name = "borrower_name")
    private String borrowerName;

    @Column(name = "issue_date") // Matches your 'issue_date' column exactly
    private LocalDate issueDate;

    @Column(name = "return_date") // Matches your 'return_date' column exactly
    private LocalDate returnDate;

    @Column(name = "fine_amount") // Matches your 'fine_amount' column exactly
    private double fineAmount;

    public LogEntry() {}

    // Getters and Setters
    public String getBorrowerName() { return borrowerName; }
    public void setBorrowerName(String borrowerName) { this.borrowerName = borrowerName; }

    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public double getFineAmount() { return fineAmount; }
    public void setFineAmount(double fineAmount) { this.fineAmount = fineAmount; }
}