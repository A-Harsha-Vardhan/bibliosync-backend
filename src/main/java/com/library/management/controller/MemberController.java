package com.library.management.controller;

import com.library.management.entity.Member;
import com.library.management.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class MemberController {

    @Autowired
    private MemberService service;

    @PostMapping("/register")
    public Member register(@RequestBody Member member) {
        return service.registerMember(member);
    }

    @PostMapping("/login")
    public Member login(@RequestBody Map<String, String> credentials) {
        // Mapping 'username' from React to the email logic in the service
        String email = credentials.get("username");
        String password = credentials.get("password");
        
        Member member = service.login(email);
        
        if (member != null && member.getPassword().equals(password)) {
            return member;
        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }
}