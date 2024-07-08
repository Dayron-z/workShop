package com.example.WorkShop.infrastructure.services;

import com.example.WorkShop.api.dto.request.ReservationRequest;
import com.example.WorkShop.api.dto.response.used_responses.ReservationResponse;
import com.example.WorkShop.domain.entities.Book;
import com.example.WorkShop.domain.entities.Reservation;
import com.example.WorkShop.domain.entities.UserEntity;
import com.example.WorkShop.domain.repositories.BookRepository;
import com.example.WorkShop.domain.repositories.ReservartionRepository;
import com.example.WorkShop.domain.repositories.UserRepository;
import com.example.WorkShop.infrastructure.abstract_services.IReservartionService;
import com.example.WorkShop.mappers.ReservationMapper;
import com.example.WorkShop.util.enums.SortType;
import com.example.WorkShop.util.enums.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationService implements IReservartionService {

    @Autowired
    private final ReservationMapper reservationMapper;
    @Autowired
    private final ReservartionRepository reservartionRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final BookRepository bookRepository;




    @Override
    public ReservationResponse create(ReservationRequest request) {
        Book book = this.bookRepository.findById(request.getBook_id()).orElseThrow(()-> new BadRequestException("Book could not be found"));
        UserEntity user = this.userRepository.findById(request.getUser_id()).orElseThrow(()-> new BadRequestException("User could not be found"));


        Reservation reservation = this.reservationMapper.requestToEntity(request);
        reservation.setReservationDate(LocalDateTime.now());
        reservation.setBook(book);
        reservation.setUser(user);
        return  this.reservationMapper.entityToResponse(this.reservartionRepository.save(reservation));
    }

    @Override
    public ReservationResponse get(Long id) {
        return this.reservationMapper.entityToResponse(this.findById(id));
    }

    @Override
    public ReservationResponse update(ReservationRequest request, Long id) {
        Reservation reservation = this.findById(id);
        Reservation updatedReservation = this.reservationMapper.requestToEntity(request);
        updatedReservation.setId(reservation.getId());
        updatedReservation.setReservationDate(reservation.getReservationDate());
        updatedReservation.setBook(reservation.getBook());
        updatedReservation.setUser(reservation.getUser());
        return this.reservationMapper.entityToResponse(this.reservartionRepository.save(updatedReservation));
    }

    @Override
    public void delete(Long id) {
        Reservation reservation = this.findById(id);
        this.reservartionRepository.delete(reservation);
    }

    @Override
    public Page<ReservationResponse> getAll(int page, int size, SortType sort) {
        if (page < 0){
            page = 0;
        };

        PageRequest pageRequest =  null;

        switch (sort){
            case NONE -> pageRequest = PageRequest.of(page, size);
            case ASC -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.reservartionRepository.findAll(pageRequest).map(this.reservationMapper::entityToResponse);
    }


    private Reservation findById(Long id){
        return this.reservartionRepository.findById(id).orElseThrow(() -> new BadRequestException("Reservation could not be found"));
    }

    @Override
    public List<ReservationResponse> findByUserId(Long id) {
        List<Reservation> reservations = reservartionRepository.findByUserId(id);
        return reservations.stream()
                .map(reservation -> reservationMapper.entityToResponse(reservation))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationResponse> findByBookId(Long id) {
        List<Reservation> reservations = reservartionRepository.findByBookId(id);
        return reservations.stream().map(reservation -> this.reservationMapper.entityToResponse(reservation)).collect(Collectors.toList());
    }
}
