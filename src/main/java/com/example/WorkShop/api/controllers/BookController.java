package com.example.WorkShop.api.controllers;

import com.example.WorkShop.api.dto.request.BookRequest;
import com.example.WorkShop.api.dto.response.used_responses.BookResponse;
import com.example.WorkShop.infrastructure.abstract_services.IBookService;
import com.example.WorkShop.util.enums.SortType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Tag(name = "Book", description = "Operations related to books")
@RequestMapping("/book")
@RestController
@AllArgsConstructor
public class BookController {
    @Autowired
    private final IBookService iBookService;

    @Operation(summary = "Get all books", description = "Retrieve a paginated list of books")
    @GetMapping
    ResponseEntity<Page<BookResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sort) {
        if (Objects.isNull(sort))
            sort = SortType.NONE;
        return ResponseEntity.ok(this.iBookService.getAll(page - 1, size, sort));
    }

    @Operation(summary = "Get book by ID", description = "Retrieve details of a specific book by its ID")
    @GetMapping("/{id}")
    ResponseEntity<BookResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.iBookService.get(id));
    }

    @Operation(summary = "Create a new book", description = "Create a new book with the provided details")
    @PostMapping
    ResponseEntity<BookResponse> create(@RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(this.iBookService.create(bookRequest));
    }

    @Operation(summary = "Update an existing book", description = "Update the details of an existing book by its ID")
    @PutMapping("/{id}")
    ResponseEntity<BookResponse> update(@RequestBody BookRequest bookRequest, @PathVariable Long id) {
        return ResponseEntity.ok(this.iBookService.update(bookRequest, id));
    }

    @Operation(summary = "Delete a book", description = "Delete a book by its ID")
    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable Long id) {
        this.iBookService.delete(id);
        return ResponseEntity.ok("The book was successfully deleted.");
    }
}
