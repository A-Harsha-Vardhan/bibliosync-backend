package com.library.management.repository;

import com.library.management.entity.BorrowRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BorrowRequestRepository extends JpaRepository<BorrowRequest, Long> {
    // This allows the Admin to find only the "PENDING" ones
    List<BorrowRequest> findByStatus(String status);
}