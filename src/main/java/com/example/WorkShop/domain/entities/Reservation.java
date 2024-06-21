    package com.example.WorkShop.domain.entities;


    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import org.hibernate.annotations.CreationTimestamp;

    import java.time.LocalDate;
    import java.time.LocalDateTime;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Entity(name = "reservation")
    public class Reservation {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(nullable = false)
        @CreationTimestamp
        private LocalDateTime reservationDate;
        @Column(nullable = false)
        private String status;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        private UserEntity user;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "book_id", referencedColumnName = "id")
        private Book book;
    }
