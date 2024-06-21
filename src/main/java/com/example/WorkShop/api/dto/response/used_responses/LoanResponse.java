package com.example.WorkShop.api.dto.response.used_responses;

import com.example.WorkShop.api.dto.response.custom_responses.BookBasicResponse;
import com.example.WorkShop.api.dto.response.custom_responses.UserBasicResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanResponse {
    private Long id;
    private LocalDateTime loan_date;
    private LocalDate return_date;
    private String status;
    private UserBasicResponse user;
    private BookBasicResponse book;
}
