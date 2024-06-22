package com.example.WorkShop.api.controllers;


import com.example.WorkShop.api.dto.request.UserRequest;
import com.example.WorkShop.api.dto.response.used_responses.UserResponse;
import com.example.WorkShop.infrastructure.abstract_services.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
@AllArgsConstructor
public class UserController {
    @Autowired
    private final IUserService iUserService;

    @PostMapping
    ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest){
         return ResponseEntity.ok(this.iUserService.create(userRequest));
    }
}
