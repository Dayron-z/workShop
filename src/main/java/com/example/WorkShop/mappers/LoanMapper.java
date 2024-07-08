package com.example.WorkShop.mappers;


import com.example.WorkShop.api.dto.request.LoanRequest;
import com.example.WorkShop.api.dto.response.used_responses.LoanResponse;
import com.example.WorkShop.domain.entities.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoanMapper {
    //EntityToReponse
    Loan requestToEntity (LoanRequest loanRequest);

    LoanResponse entityToResponse (Loan loan);
}
