package com.example.WorkShop.domain.entities;


import com.example.WorkShop.util.enums.Type;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false, length = 100)
    private String fullName;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = false, cascade = CascadeType.ALL, mappedBy = "user")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Reservation> rerservations;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = false, cascade = CascadeType.ALL, mappedBy = "user")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Loan> loans;

}
