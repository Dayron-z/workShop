package com.example.WorkShop.api.dto.response.used_responses;

import com.example.WorkShop.api.dto.response.custom_responses.LoanResponseToUserResponse;
import com.example.WorkShop.api.dto.response.custom_responses.ReservationResponseToUserResponse;
import com.example.WorkShop.util.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class    UserResponse {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private Type type;
    private List<ReservationResponseToUserResponse> rerservations;
    private List<LoanResponseToUserResponse> loans;
}
