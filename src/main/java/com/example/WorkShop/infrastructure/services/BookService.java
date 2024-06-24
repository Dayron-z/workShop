package com.example.WorkShop.infrastructure.services;


import com.example.WorkShop.api.dto.request.BookRequest;
import com.example.WorkShop.api.dto.request.UserRequest;
import com.example.WorkShop.api.dto.response.used_responses.BookResponse;
import com.example.WorkShop.api.dto.response.used_responses.UserResponse;
import com.example.WorkShop.domain.entities.Book;
import com.example.WorkShop.domain.entities.UserEntity;
import com.example.WorkShop.domain.repositories.BookRepository;
import com.example.WorkShop.domain.repositories.UserRepository;
import com.example.WorkShop.infrastructure.abstract_services.IBookService;
import com.example.WorkShop.infrastructure.abstract_services.IUserService;
import com.example.WorkShop.mappers.BookMapper;
import com.example.WorkShop.mappers.UserMapper;
import com.example.WorkShop.util.enums.SortType;
import com.example.WorkShop.util.enums.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService implements IBookService {
    /*Repositorio*/
    @Autowired
    private final BookRepository bookRepository;
    /*Mapper*/
    @Autowired
    private final BookMapper bookMapper;


    @Override
    public BookResponse create(BookRequest request) {
        /*Verificadp*/
        Book book = this.bookMapper.requestToEntity(request);
        return this.bookMapper.entityToResponse(this.bookRepository.save(book));
    }

    @Override
    public BookResponse get(Long id) {
        Book book = this.findById(id);
        return this.bookMapper.entityToResponse(book);
    }
    @Override
    public BookResponse update(BookRequest request, Long id) {
        Book book = this.findById(id);
        Book updatedBook = this.bookMapper.requestToEntity(request);
        updatedBook.setId(book.getId());
        updatedBook.setLoans(book.getLoans());
        updatedBook.setRerservations(book.getRerservations());
        return this.bookMapper.entityToResponse(this.bookRepository.save(updatedBook));
    }
    @Override
    public void delete(Long id) {
        Book book = this.findById(id);
        this.bookRepository.delete(book);
    }
    @Override
    public Page<BookResponse> getAll(int page, int size, SortType sort) {
        if (page < 0){
            page = 0;
        };

        PageRequest pageRequest =  null;

        switch (sort){
            case NONE -> pageRequest = PageRequest.of(page, size);
            case ASC -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.bookRepository.findAll(pageRequest).map(this.bookMapper::entityToResponse);
    }
    private Book findById(Long id){
        return this.bookRepository.findById(id).orElseThrow(() -> new BadRequestException("Book could not be found"));
    }
}
