package com.example.WorkShop.domain.repositories;

import com.example.WorkShop.domain.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Reservation, Long> {
}

