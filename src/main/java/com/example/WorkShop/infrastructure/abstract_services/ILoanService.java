package com.example.WorkShop.infrastructure.abstract_services;

import com.example.WorkShop.api.dto.request.BookRequest;
import com.example.WorkShop.api.dto.request.LoanRequest;
import com.example.WorkShop.api.dto.response.used_responses.BookResponse;
import com.example.WorkShop.api.dto.response.used_responses.LoanResponse;

public interface ILoanService extends CrudService<LoanRequest, LoanResponse, Long>{
}
