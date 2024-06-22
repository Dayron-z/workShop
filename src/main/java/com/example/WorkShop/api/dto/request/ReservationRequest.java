package com.example.WorkShop.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {
    @NotBlank(message = "Status cannot be null")
    private String status;

    @NotNull(message = "user_id cannot be null")
    @PositiveOrZero(message = "user_id must be positive or zero")
    private Long user_id;

    @NotNull(message = "book_id cannot be null")
    @PositiveOrZero(message = "book_id must be positive or zero")
    private Long book_id;
}
