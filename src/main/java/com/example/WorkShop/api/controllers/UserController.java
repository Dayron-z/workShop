package com.example.WorkShop.api.controllers;

import com.example.WorkShop.api.dto.request.UserRequest;
import com.example.WorkShop.api.dto.response.used_responses.UserResponse;
import com.example.WorkShop.infrastructure.abstract_services.IUserService;
import com.example.WorkShop.util.enums.SortType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Tag(name = "User", description = "Operations related to users")
@RequestMapping("/user")
@RestController
@AllArgsConstructor
public class UserController {
    @Autowired
    private final IUserService iUserService;

    @Operation(summary = "Get all users", description = "Retrieve a paginated list of users")
    @GetMapping
    ResponseEntity<Page<UserResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sort) {
        if (Objects.isNull(sort))
            sort = SortType.NONE;
        return ResponseEntity.ok(this.iUserService.getAll(page - 1, size, sort));
    }

    @Operation(summary = "Create a new user", description = "Create a new user with the provided details")
    @PostMapping
    ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(this.iUserService.create(userRequest));
    }

    @Operation(summary = "Update an existing user", description = "Update the details of an existing user by its ID")
    @PutMapping("/{id}")
    ResponseEntity<UserResponse> update(@RequestBody UserRequest userRequest, @PathVariable Long id) {
        return ResponseEntity.ok(this.iUserService.update(userRequest, id));
    }

    @Operation(summary = "Delete a user", description = "Delete a user by its ID")
    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable Long id) {
        this.iUserService.delete(id);
        return ResponseEntity.ok("The user was successfully deleted.");
    }
}

