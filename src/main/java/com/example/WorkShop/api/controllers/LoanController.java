package com.example.WorkShop.api.controllers;

import com.example.WorkShop.api.dto.request.LoanRequest;
import com.example.WorkShop.api.dto.response.used_responses.LoanResponse;
import com.example.WorkShop.infrastructure.abstract_services.ILoanService;
import com.example.WorkShop.util.enums.SortType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Tag(name = "Loan", description = "Operations related to loans")
@RequestMapping("/loan")
@RestController
@AllArgsConstructor
public class LoanController {
    @Autowired
    private final ILoanService iLoanService;

    @Operation(summary = "Get all loans", description = "Retrieve a paginated list of loans")
    @GetMapping
    ResponseEntity<Page<LoanResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sort) {
        if (Objects.isNull(sort))
            sort = SortType.NONE;
        return ResponseEntity.ok(this.iLoanService.getAll(page - 1, size, sort));
    }

    @Operation(summary = "Get loan by ID", description = "Retrieve details of a specific loan by its ID")
    @GetMapping("/{id}")
    ResponseEntity<LoanResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.iLoanService.get(id));
    }

    @Operation(summary = "Create a new loan", description = "Create a new loan with the provided details")
    @PostMapping
    ResponseEntity<LoanResponse> create(@RequestBody LoanRequest loanRequest) {
        return ResponseEntity.ok(this.iLoanService.create(loanRequest));
    }

    @Operation(summary = "Update an existing loan", description = "Update the details of an existing loan by its ID")
    @PutMapping("/{id}")
    ResponseEntity<LoanResponse> update(@RequestBody LoanRequest loanRequest, @PathVariable Long id) {
        return ResponseEntity.ok(this.iLoanService.update(loanRequest, id));
    }

    @Operation(summary = "Delete a loan", description = "Delete a loan by its ID")
    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable Long id) {
        this.iLoanService.delete(id);
        return ResponseEntity.ok("The loan was successfully deleted.");
    }
}
