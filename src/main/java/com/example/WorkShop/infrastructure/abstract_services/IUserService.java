package com.example.WorkShop.infrastructure.abstract_services;

import com.example.WorkShop.api.dto.request.BookRequest;
import com.example.WorkShop.api.dto.response.used_responses.BookResponse;

public interface IUserService extends CrudService<BookRequest, BookResponse, Long>{
}
