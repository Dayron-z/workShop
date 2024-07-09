package com.example.WorkShop.infrastructure.services;


import com.example.WorkShop.api.dto.request.UserRequest;
import com.example.WorkShop.api.dto.response.used_responses.UserResponse;
import com.example.WorkShop.domain.entities.UserEntity;
import com.example.WorkShop.domain.repositories.UserRepository;
import com.example.WorkShop.infrastructure.abstract_services.IUserService;
import com.example.WorkShop.mappers.UserMapper;
import com.example.WorkShop.util.enums.SortType;
import com.example.WorkShop.util.exceptions.BadRequestException;
import com.example.WorkShop.util.exceptions.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        /*Método de validaciones*/
        validateUser(request);

        UserEntity userEntity = this.userMapper.requestToEntity(request);
        return this.userMapper.entityToResponse(this.userRepository.save(userEntity));
    }
    @Override
    public UserResponse get(Long id) {
        UserEntity user = this.findById(id);
        return this.userMapper.entityToResponse(user);
    }
    @Override
    public UserResponse update(UserRequest request, Long id) {
        /*Método de validaciones*/
        validateUser(request);

        UserEntity user = this.findById(id);
        UserEntity userEntity = this.userMapper.requestToEntity(request);
        userEntity.setId(user.getId());
        userEntity.setLoans(user.getLoans());
        userEntity.setRerservations(user.getRerservations());
        return this.userMapper.entityToResponse(this.userRepository.save(userEntity));
    }
    @Override
    public void delete(Long id) {
        UserEntity user = this.findById(id);
        this.userRepository.delete(user);
    }
    @Override
    public Page<UserResponse> getAll(int page, int size, SortType sort) {
        if (page < 0){
            page = 0;
        };

        PageRequest pageRequest =  null;

        switch (sort){
            case NONE -> pageRequest = PageRequest.of(page, size);
            case ASC -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.userRepository.findAll(pageRequest).map(this.userMapper::entityToResponse);
    }

    private UserEntity findById(Long id){
        return this.userRepository.findById(id).orElseThrow(() -> new BadRequestException("User could not be found"));
    }

    private void validateUser(UserRequest userRequest){
        if (userRepository.existsByUsername(userRequest.getUsername())){
            throw new ValidationException("Username already exists");
        }
        if (userRepository.existsByEmail(userRequest.getEmail())){
            throw new ValidationException("Email already exists");
        }
    }


}
