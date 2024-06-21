package com.example.WorkShop.infrastructure.abstract_services;

import com.example.WorkShop.api.dto.request.LoanRequest;
import com.example.WorkShop.api.dto.request.ReservationRequest;
import com.example.WorkShop.api.dto.response.used_responses.LoanResponse;
import com.example.WorkShop.api.dto.response.used_responses.ReservationResponse;

public interface IReservartionService extends CrudService<ReservationRequest, ReservationResponse, Long>{
}
