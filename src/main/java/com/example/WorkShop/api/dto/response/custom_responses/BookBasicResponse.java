package com.example.WorkShop.api.dto.response.custom_responses;

import com.example.WorkShop.domain.entities.Loan;
import com.example.WorkShop.domain.entities.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookBasicResponse {
    private Long id;
    private String title;
    private String author;
    private Integer publicationYear;
    private String genre;
    private String isbn;
}
