package com.example.WorkShop.domain.repositories;

import com.example.WorkShop.domain.entities.Book;
import com.example.WorkShop.domain.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReservartionRepository extends JpaRepository<Reservation, Long> {
}

