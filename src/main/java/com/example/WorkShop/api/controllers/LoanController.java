package com.example.WorkShop.api.controllers;


import com.example.WorkShop.api.dto.request.BookRequest;
import com.example.WorkShop.api.dto.request.LoanRequest;
import com.example.WorkShop.api.dto.response.used_responses.BookResponse;
import com.example.WorkShop.api.dto.response.used_responses.LoanResponse;
import com.example.WorkShop.infrastructure.abstract_services.IBookService;
import com.example.WorkShop.infrastructure.abstract_services.ILoanService;
import com.example.WorkShop.util.enums.SortType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequestMapping("/loan")
@RestController
@AllArgsConstructor
public class LoanController {
    @Autowired
    private final ILoanService iLoanService;

    @GetMapping
    ResponseEntity<Page<LoanResponse>> getAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestHeader(required = false) SortType sort){
        if (Objects.isNull(sort))
            sort = SortType.NONE;
        return ResponseEntity.ok(this.iLoanService.getAll(page - 1, size, sort));
    }

    @GetMapping("/{id}")
    ResponseEntity<LoanResponse>getById(@PathVariable Long id){
        return ResponseEntity.ok(this.iLoanService.get(id));
    }

    @PostMapping
    ResponseEntity<LoanResponse> create(@RequestBody LoanRequest loanRequest){
        return ResponseEntity.ok(this.iLoanService.create(loanRequest));
    }

    @PutMapping("/{id}")
    ResponseEntity<LoanResponse> update(@RequestBody LoanRequest loanRequest, @PathVariable Long id){
        return ResponseEntity.ok(this.iLoanService.update(loanRequest, id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable Long id){
        this.iLoanService.delete(id);
        return ResponseEntity.ok("The loan was successfully deleted.");
    }
}
