package com.spring.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<String>> findAll() {
        return ResponseEntity.ok(List.of("Book1", "Book2", "Book3"));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> create(@RequestBody String data) {
        return ResponseEntity.ok(data);
    }
}
