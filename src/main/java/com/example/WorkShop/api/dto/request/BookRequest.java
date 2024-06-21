package com.example.WorkShop.api.dto.request;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequest {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @Min(value = 1000, message = "Publication year must be greater than or equal to 1000")
    @Max(value = 9999, message = "Publication year must be less than or equal to 9999")
    private Integer publicationYear;

    @NotBlank(message = "Genre is required")
    private String genre;


    @NotBlank(message = "ISBN is required")
/*    Define un patrón regex (\\d{13}) para asegurar que isbn tenga exactamente 13 dígitos numéricos.*/
    @Pattern(regexp = "\\d{13}", message = "ISBN must be a 13-digit number")
    private String isbn;
}
