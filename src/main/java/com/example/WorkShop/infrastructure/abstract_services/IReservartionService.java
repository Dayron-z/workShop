package com.example.WorkShop.infrastructure.abstract_services;

import com.example.WorkShop.api.dto.request.LoanRequest;
import com.example.WorkShop.api.dto.request.ReservationRequest;
import com.example.WorkShop.api.dto.response.used_responses.LoanResponse;
import com.example.WorkShop.api.dto.response.used_responses.ReservationResponse;
import com.example.WorkShop.domain.entities.Reservation;

import java.util.List;

public interface IReservartionService extends CrudService<ReservationRequest, ReservationResponse, Long>{
    public List<ReservationResponse> findByUserId (Long id);
    public List<ReservationResponse> findByBookId (Long id);
    public String FIELD_BY_SORT = "id";
}
