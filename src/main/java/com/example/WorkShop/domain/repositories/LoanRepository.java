package com.example.WorkShop.domain.repositories;

import com.example.WorkShop.domain.entities.Book;
import com.example.WorkShop.domain.entities.Loan;
import com.example.WorkShop.domain.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    public List<Loan> findByUserId (Long id);
}
