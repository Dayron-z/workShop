package com.example.WorkShop.api.controllers;


import com.example.WorkShop.api.dto.request.BookRequest;
import com.example.WorkShop.api.dto.request.ReservationRequest;
import com.example.WorkShop.api.dto.response.used_responses.BookResponse;
import com.example.WorkShop.api.dto.response.used_responses.ReservationResponse;
import com.example.WorkShop.domain.entities.Reservation;
import com.example.WorkShop.infrastructure.abstract_services.IBookService;
import com.example.WorkShop.infrastructure.abstract_services.IReservartionService;
import com.example.WorkShop.util.enums.SortType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping("/reservation")
@RestController
@AllArgsConstructor
public class ReservationController {
    @Autowired
    private final IReservartionService iReservartionService;
    @GetMapping
    ResponseEntity<Page<ReservationResponse>> getAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestHeader(required = false) SortType sort){
        if (Objects.isNull(sort))
            sort = SortType.NONE;
        return ResponseEntity.ok(this.iReservartionService.getAll(page - 1, size, sort));
    }

    @GetMapping("/{id}")
    ResponseEntity<ReservationResponse>getById(@PathVariable Long id){
        return ResponseEntity.ok(this.iReservartionService.get(id));
    }

    @GetMapping("/users/{user_id}/reservations")
    public ResponseEntity<List<ReservationResponse>> getReservationsByUser(@PathVariable("user_id") Long userId) {
        return ResponseEntity.ok(this.iReservartionService.findByUserId(userId));
    }

    @GetMapping("/books/{book_id}/reservations")
    public ResponseEntity<List<ReservationResponse>> getReservationsByBook(@PathVariable("book_id") Long bookId) {
        return ResponseEntity.ok(this.iReservartionService.findByBookId(bookId));
    }


    @PostMapping
    ResponseEntity<ReservationResponse> create(@RequestBody ReservationRequest reservationRequest){
         return ResponseEntity.ok(this.iReservartionService.create(reservationRequest));
    }

    @PutMapping("/{id}")
    ResponseEntity<ReservationResponse> update(@RequestBody ReservationRequest reservationRequest, @PathVariable Long id){
        return ResponseEntity.ok(this.iReservartionService.update(reservationRequest, id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable Long id){
        this.iReservartionService.delete(id);
        return ResponseEntity.ok("The book was successfully deleted.");
    }


}
