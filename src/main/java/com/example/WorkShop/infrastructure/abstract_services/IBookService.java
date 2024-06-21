package com.example.WorkShop.infrastructure.abstract_services;

import com.example.WorkShop.api.dto.request.BookRequest;
import com.example.WorkShop.api.dto.response.used_responses.BookResponse;

public interface IBookService extends CrudService<BookRequest, BookResponse, Long>{
}
