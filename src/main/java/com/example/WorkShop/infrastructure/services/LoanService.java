package com.example.WorkShop.infrastructure.services;

import com.example.WorkShop.api.dto.request.LoanRequest;
import com.example.WorkShop.api.dto.response.used_responses.LoanResponse;
import com.example.WorkShop.domain.entities.Book;
import com.example.WorkShop.domain.entities.Loan;
import com.example.WorkShop.domain.entities.Reservation;
import com.example.WorkShop.domain.entities.UserEntity;
import com.example.WorkShop.domain.repositories.BookRepository;
import com.example.WorkShop.domain.repositories.LoanRepository;
import com.example.WorkShop.domain.repositories.UserRepository;
import com.example.WorkShop.infrastructure.abstract_services.ILoanService;
import com.example.WorkShop.mappers.LoanMapper;
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
public class LoanService  implements ILoanService {

    @Autowired
    private final LoanMapper loanMapper;
    @Autowired
    private final LoanRepository loanRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final BookRepository bookRepository;



    @Override
    public LoanResponse create(LoanRequest request) {
        Book book = this.bookRepository.findById(request.getBook_id()).orElseThrow(()-> new BadRequestException("Book could not be found"));
        UserEntity user = this.userRepository.findById(request.getBook_id()).orElseThrow(()-> new BadRequestException("User could not be found"));


        Loan loan = this.loanMapper.requestToEntity(request);
        loan.setLoan_date(LocalDateTime.now());
        loan.setBook(book);
        loan.setUser(user);
        return  this.loanMapper.entityToResponse(this.loanRepository.save(loan));
    }

    @Override
    public LoanResponse get(Long id) {
        return this.loanMapper.entityToResponse(this.findById(id));
    }

    @Override
    public LoanResponse update(LoanRequest request, Long id) {
        Loan loan = this.findById(id);
        Loan updatedLoan = this.loanMapper.requestToEntity(request);
        updatedLoan.setId(loan.getId());
        updatedLoan.setLoan_date(loan.getLoan_date());
        updatedLoan.setBook(loan.getBook());
        updatedLoan.setUser(loan.getUser());
        return this.loanMapper.entityToResponse(this.loanRepository.save(updatedLoan));
    }

    @Override
    public void delete(Long id) {
        Loan loan = this.findById(id);
        this.loanRepository.delete(loan);
    }

    @Override
    public Page<LoanResponse> getAll(int page, int size, SortType sort) {
        if (page < 0){
            page = 0;
        };

        PageRequest pageRequest =  null;

        switch (sort){
            case NONE -> pageRequest = PageRequest.of(page, size);
            case ASC -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.loanRepository.findAll(pageRequest).map(this.loanMapper::entityToResponse);
    }


    private Loan findById(Long id){
        return this.loanRepository.findById(id).orElseThrow(() -> new BadRequestException("Loan could not be found"));
    }

    @Override
    public List<LoanResponse> findByUserId(Long id) {
        List<Loan> loans = loanRepository.findByUserId(id);
        return loans.stream()
                .map(reservation -> loanMapper.entityToResponse(reservation))
                .collect(Collectors.toList());
    }
}
