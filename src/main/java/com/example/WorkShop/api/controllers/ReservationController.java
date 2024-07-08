package com.example.WorkShop.api.controllers;

import com.example.WorkShop.api.dto.request.ReservationRequest;
import com.example.WorkShop.api.dto.response.used_responses.ReservationResponse;
import com.example.WorkShop.infrastructure.abstract_services.IReservartionService;
import com.example.WorkShop.util.enums.SortType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Tag(name = "Reservation", description = "Operations related to reservations")
@RequestMapping("/reservation")
@RestController
@AllArgsConstructor
public class ReservationController {
    @Autowired
    private final IReservartionService iReservartionService;

    @Operation(summary = "Get all reservations", description = "Retrieve a paginated list of reservations")
    @GetMapping
    ResponseEntity<Page<ReservationResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sort) {
        if (Objects.isNull(sort))
            sort = SortType.NONE;
        return ResponseEntity.ok(this.iReservartionService.getAll(page - 1, size, sort));
    }

    @Operation(summary = "Get reservation by ID", description = "Retrieve details of a specific reservation by its ID")
    @GetMapping("/{id}")
    ResponseEntity<ReservationResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.iReservartionService.get(id));
    }

    @Operation(summary = "Get reservations by user ID", description = "Retrieve a list of reservations for a specific user by their ID")
    @GetMapping("/users/{user_id}/reservations")
    public ResponseEntity<List<ReservationResponse>> getReservationsByUser(@PathVariable("user_id") Long userId) {
        return ResponseEntity.ok(this.iReservartionService.findByUserId(userId));
    }

    @Operation(summary = "Get reservations by book ID", description = "Retrieve a list of reservations for a specific book by its ID")
    @GetMapping("/books/{book_id}/reservations")
    public ResponseEntity<List<ReservationResponse>> getReservationsByBook(@PathVariable("book_id") Long bookId) {
        return ResponseEntity.ok(this.iReservartionService.findByBookId(bookId));
    }

    @Operation(summary = "Create a new reservation", description = "Create a new reservation with the provided details")
    @PostMapping
    ResponseEntity<ReservationResponse> create(@RequestBody ReservationRequest reservationRequest) {
        return ResponseEntity.ok(this.iReservartionService.create(reservationRequest));
    }

    @Operation(summary = "Update an existing reservation", description = "Update the details of an existing reservation by its ID")
    @PutMapping("/{id}")
    ResponseEntity<ReservationResponse> update(@RequestBody ReservationRequest reservationRequest, @PathVariable Long id) {
        return ResponseEntity.ok(this.iReservartionService.update(reservationRequest, id));
    }

    @Operation(summary = "Delete a reservation", description = "Delete a reservation by its ID")
    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable Long id) {
        this.iReservartionService.delete(id);
        return ResponseEntity.ok("The reservation was successfully deleted.");
    }
}
