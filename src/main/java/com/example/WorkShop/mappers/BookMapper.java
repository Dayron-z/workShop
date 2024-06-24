package com.example.WorkShop.mappers;


/*Por qué es una interfaz
* Es correcto hacer una inyeccion de dependencias usando metodos de una interfaz?
*
* */


import com.example.WorkShop.api.dto.request.BookRequest;
import com.example.WorkShop.api.dto.request.UserRequest;
import com.example.WorkShop.api.dto.response.used_responses.BookResponse;
import com.example.WorkShop.api.dto.response.used_responses.UserResponse;
import com.example.WorkShop.domain.entities.Book;
import com.example.WorkShop.domain.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {
    BookResponse entityToResponse(Book book);
    Book requestToEntity(BookRequest userRequest);
}
