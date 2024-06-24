package com.example.WorkShop.api.controllers;


import com.example.WorkShop.api.dto.request.BookRequest;
import com.example.WorkShop.api.dto.request.UserRequest;
import com.example.WorkShop.api.dto.response.used_responses.BookResponse;
import com.example.WorkShop.api.dto.response.used_responses.UserResponse;
import com.example.WorkShop.infrastructure.abstract_services.IBookService;
import com.example.WorkShop.infrastructure.abstract_services.IUserService;
import com.example.WorkShop.util.enums.SortType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequestMapping("/book")
@RestController
@AllArgsConstructor
public class BookController {
    @Autowired
    private final IBookService iBookService;


    @GetMapping
    ResponseEntity<Page<BookResponse>> getAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestHeader(required = false) SortType sort){
        if (Objects.isNull(sort))
            sort = SortType.NONE;
        return ResponseEntity.ok(this.iBookService.getAll(page - 1, size, sort));
    }

    @GetMapping("/{id}")
    ResponseEntity<BookResponse>getById(@PathVariable Long id){
        return ResponseEntity.ok(this.iBookService.get(id));
    }

    @PostMapping
    ResponseEntity<BookResponse> create(@RequestBody BookRequest bookRequest){
         return ResponseEntity.ok(this.iBookService.create(bookRequest));
    }

    @PutMapping("/{id}")
    ResponseEntity<BookResponse> update(@RequestBody BookRequest bookRequest, @PathVariable Long id){
        return ResponseEntity.ok(this.iBookService.update(bookRequest, id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable Long id){
        this.iBookService.delete(id);
        return ResponseEntity.ok("The book was successfully deleted.");
    }


}
