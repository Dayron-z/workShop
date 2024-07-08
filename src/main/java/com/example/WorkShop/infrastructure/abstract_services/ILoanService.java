package com.example.WorkShop.infrastructure.abstract_services;

import com.example.WorkShop.api.dto.request.BookRequest;
import com.example.WorkShop.api.dto.request.LoanRequest;
import com.example.WorkShop.api.dto.response.used_responses.BookResponse;
import com.example.WorkShop.api.dto.response.used_responses.LoanResponse;
import com.example.WorkShop.api.dto.response.used_responses.ReservationResponse;

import java.util.List;

public interface ILoanService extends CrudService<LoanRequest, LoanResponse, Long>{
    public List<LoanResponse> findByUserId (Long id);
    public String FIELD_BY_SORT = "id";
}
