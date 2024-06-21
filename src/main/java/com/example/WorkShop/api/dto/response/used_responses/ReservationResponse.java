package com.example.WorkShop.api.dto.response.used_responses;

import com.example.WorkShop.api.dto.response.custom_responses.BookBasicResponse;
import com.example.WorkShop.api.dto.response.custom_responses.UserBasicResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationResponse {
    private Long id;
    private String status;
    private UserBasicResponse user;
    private BookBasicResponse book;
}
