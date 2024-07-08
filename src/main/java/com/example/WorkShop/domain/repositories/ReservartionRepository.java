package com.example.WorkShop.domain.repositories;

import com.example.WorkShop.domain.entities.Book;
import com.example.WorkShop.domain.entities.Reservation;
import com.example.WorkShop.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReservartionRepository extends JpaRepository<Reservation, Long> {
    public List<Reservation> findByUserId (Long id);
    public List<Reservation> findByBookId (Long id);
}

