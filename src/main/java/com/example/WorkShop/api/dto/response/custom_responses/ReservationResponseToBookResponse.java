package com.example.WorkShop.api.dto.response.custom_responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseToBookResponse {
    private Long id;
    private String status;
    private UserBasicResponse user;
}
