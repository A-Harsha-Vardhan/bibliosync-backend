package com.library.management.controller;

import com.library.management.entity.BorrowRequest;
import com.library.management.entity.Member;
import com.library.management.repository.BorrowRequestRepository; // Added Import
import com.library.management.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*") // Changed to "*" so it works on Render, not just localhost
@RestController
@RequestMapping("/api/auth")
public class MemberController {

    @Autowired
    private MemberService service;

    // --- CRITICAL: Injected the missing repository ---
    @Autowired
    private BorrowRequestRepository borrowRequestRepository;

    @PostMapping("/register")
    public Member register(@RequestBody Member member) {
        return service.registerMember(member);
    }

    @PostMapping("/login")
    public Member login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("username");
        String password = credentials.get("password");
        
        Member member = service.login(email);
        
        if (member != null && member.getPassword().equals(password)) {
            return member;
        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }

    // --- The endpoint the Admin Portal calls ---
    @GetMapping("/pending")
    public List<BorrowRequest> getPendingRequests() {
        // This will fetch from the borrow_request table where status is 'PENDING'
        return borrowRequestRepository.findByStatus("PENDING");
    }
}