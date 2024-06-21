package com.example.WorkShop.api.dto.response.custom_responses;

import com.example.WorkShop.domain.entities.Loan;
import com.example.WorkShop.domain.entities.Reservation;
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
public class UserBasicResponse {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private Type type;
}
