package com.example.WorkShop.mappers;


/*Por qué es una interfaz
* Es correcto hacer una inyeccion de dependencias usando metodos de una interfaz?
*
* */


import com.example.WorkShop.api.dto.request.UserRequest;
import com.example.WorkShop.api.dto.response.used_responses.BookResponse;
import com.example.WorkShop.api.dto.response.used_responses.UserResponse;
import com.example.WorkShop.domain.entities.Book;
import com.example.WorkShop.domain.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    /* -En el momento en que yo paso una entidad a un response, existen atributos que yo no voy a necesitar para la construcción del response
     * Me surgen 2 dudas:
     * 1 - ¿De manera automática no se descartatía simplemente las propiedades que no se encuentran en el response?
     * 2 - De ser veridica la anterior respuesta, que utilidad tiene el ignore
     *  */


    /*¿Que es lo qué deseo hacer?
     * Deseo  hacer un mapper que convierta mi entidad en response
     * */


    UserResponse entityToResponse(UserEntity userEntity);
    UserEntity requestToEntity(UserRequest userRequest);
}
