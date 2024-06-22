package com.example.WorkShop.domain.entities;

import com.example.WorkShop.util.enums.Type;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username cannot be blank")
    @Column(nullable = false, length = 100)
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Column(nullable = false)
    private String password;

    @Email(message = "Email should be valid")
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "Full name cannot be blank")
    @Column(nullable = false, length = 100)
    private String fullName;

    @NotNull(message = "Type cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = false, cascade = CascadeType.ALL, mappedBy = "user")
    /*Excluir del ToString*/
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Reservation> rerservations = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = false, cascade = CascadeType.ALL, mappedBy = "user")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Loan> loans = new ArrayList<>();

    public UserEntity() {
        this.rerservations = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

}
