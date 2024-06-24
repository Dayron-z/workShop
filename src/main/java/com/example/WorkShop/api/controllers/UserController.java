package com.example.WorkShop.api.controllers;


import com.example.WorkShop.api.dto.request.UserRequest;
import com.example.WorkShop.api.dto.response.used_responses.UserResponse;
import com.example.WorkShop.infrastructure.abstract_services.IUserService;
import com.example.WorkShop.util.enums.SortType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequestMapping("/user")
@RestController
@AllArgsConstructor
public class UserController {
    @Autowired
    private final IUserService iUserService;


    @GetMapping
    ResponseEntity<Page<UserResponse>> getAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestHeader(required = false) SortType sort){
        if (Objects.isNull(sort))
            sort = SortType.NONE;
        return ResponseEntity.ok(this.iUserService.getAll(page - 1, size, sort));
    }

    @PostMapping
    ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest){
         return ResponseEntity.ok(this.iUserService.create(userRequest));
    }

    @PutMapping("/{id}")
    ResponseEntity<UserResponse> update(@RequestBody UserRequest userRequest, @PathVariable Long id){
        return ResponseEntity.ok(this.iUserService.update(userRequest, id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable Long id){
        this.iUserService.delete(id);
        return ResponseEntity.ok("The user was successfully deleted.");
    }


}
