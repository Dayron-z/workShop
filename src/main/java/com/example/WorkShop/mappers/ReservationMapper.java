package com.example.WorkShop.mappers;


import com.example.WorkShop.api.dto.request.LoanRequest;
import com.example.WorkShop.api.dto.request.ReservationRequest;
import com.example.WorkShop.api.dto.response.used_responses.LoanResponse;
import com.example.WorkShop.api.dto.response.used_responses.ReservationResponse;
import com.example.WorkShop.domain.entities.Loan;
import com.example.WorkShop.domain.entities.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReservationMapper {
    //EntityToReponse
    Reservation requestToEntity (ReservationRequest reservationRequest);

    ReservationResponse entityToResponse (Reservation reservation);
}
