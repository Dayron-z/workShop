package com.example.WorkShop.infrastructure.services;


import com.example.WorkShop.api.dto.request.BookRequest;
import com.example.WorkShop.api.dto.response.used_responses.BookResponse;
import com.example.WorkShop.infrastructure.abstract_services.IUserService;
import com.example.WorkShop.util.enums.SortType;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Override
    public BookResponse create(BookRequest request) {
        return null;
    }

    @Override
    public BookResponse get(Long aLong) {
        return null;
    }

    @Override
    public BookResponse update(BookRequest request, Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Page<BookResponse> getAll(int page, int size, SortType sort) {
        return null;
    }
}
