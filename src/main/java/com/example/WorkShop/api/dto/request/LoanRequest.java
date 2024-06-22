package com.example.WorkShop.api.dto.request;


import com.example.WorkShop.domain.entities.UserEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequest {
    @FutureOrPresent(message = "return_date must be in the present or future")
    private LocalDate return_date;

    @NotNull(message = "Status cannot be null")
    private String status;

    @NotNull(message = "user_id cannot be null")
    @PositiveOrZero(message = "user_id must be positive or zero")
    private Long user_id;

    @NotNull(message = "book_id cannot be null")
    @PositiveOrZero(message = "book_id must be positive or zero")
    private Long book_id;
}
