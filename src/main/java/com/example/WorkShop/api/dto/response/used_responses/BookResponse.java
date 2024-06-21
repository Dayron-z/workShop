package com.example.WorkShop.api.dto.response.used_responses;

import com.example.WorkShop.api.dto.response.custom_responses.ReservationResponseToBookResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private Integer publicationYear;
    private String genre;
    private String isbn;
    private List<ReservationResponseToBookResponse> rerservations;
    private List<ReservationResponseToBookResponse> loans;
}
