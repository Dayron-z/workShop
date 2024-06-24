package com.example.WorkShop.infrastructure.abstract_services;

import com.example.WorkShop.api.dto.request.BookRequest;
import com.example.WorkShop.api.dto.request.UserRequest;
import com.example.WorkShop.api.dto.response.used_responses.BookResponse;
import com.example.WorkShop.api.dto.response.used_responses.UserResponse;

public interface IUserService extends CrudService<UserRequest, UserResponse, Long>{
    public String FIELD_BY_SORT = "username";
}
