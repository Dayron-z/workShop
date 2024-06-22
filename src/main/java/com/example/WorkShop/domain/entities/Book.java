package com.example.WorkShop.domain.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 100)
    private String author;
    @Column(nullable = false)
    private Integer publicationYear;
    @Column(nullable = false, length = 50)
    private String genre;
    @Column(nullable = false)
    private String isbn;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = false, cascade = CascadeType.ALL, mappedBy = "book")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Reservation> rerservations;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = false, cascade = CascadeType.ALL, mappedBy = "book")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Loan> loans;
}
