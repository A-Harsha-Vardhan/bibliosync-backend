package com.library.management.service;

import com.library.management.entity.Member;
import com.library.management.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepository repo;

    public Member registerMember(Member member) {
        if(repo.findByEmail(member.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists!");
        }
        // Set default role if not provided
        if (member.getRole() == null) member.setRole("BORROWER");
        return repo.save(member);
    }

    public Member login(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}