package com.example.WorkShop.infrastructure.services;


import com.example.WorkShop.api.dto.request.BookRequest;
import com.example.WorkShop.api.dto.request.UserRequest;
import com.example.WorkShop.api.dto.response.used_responses.BookResponse;
import com.example.WorkShop.api.dto.response.used_responses.UserResponse;
import com.example.WorkShop.domain.entities.Book;
import com.example.WorkShop.domain.entities.UserEntity;
import com.example.WorkShop.domain.repositories.UserRepository;
import com.example.WorkShop.infrastructure.abstract_services.IUserService;
import com.example.WorkShop.mappers.UserMapper;
import com.example.WorkShop.util.enums.SortType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    /*Repositorio*/
    @Autowired
    private final UserRepository userRepository;
    /*Mapper*/
    @Autowired
    private final UserMapper userMapper;


    @Override
    public UserResponse create(UserRequest request) {
        /*Verificadp*/
        UserEntity userEntity = this.userMapper.requestToEntity(request);
        System.out.println(userEntity);
        return this.userMapper.entityToResponse(this.userRepository.save(userEntity));
    }

    @Override
    public UserResponse get(Long aLong) {
        return null;
    }


    @Override
    public UserResponse update(UserRequest request, Long aLong) {
        return null;
    }


    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Page<UserResponse> getAll(int page, int size, SortType sort) {
        return null;
    }

}
