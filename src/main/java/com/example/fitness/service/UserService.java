package com.example.fitness.service;

import com.example.fitness.dto.Auth.RegisterRequest;
import com.example.fitness.dto.Auth.UserResponse;
import com.example.fitness.model.User;
import com.example.fitness.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
//    without dto base direct with constructor

//    public User addUser(RegisterRequest registerRequest) {
//       return userRepository.save(registerRequest);
//    }

    public UserResponse register(RegisterRequest request) {
        User user = User.builder().email(request.getEmail()).firstName(request.getFirstName()).lastName(request.getLastName()).password(request.getPassword()).build();

        User savedUser = userRepository.save(user);
        return mapToResponse(savedUser);
    }

    private UserResponse mapToResponse(User savedUser) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(savedUser.getId());
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setPassword(savedUser.getPassword());
        userResponse.setFirstName(savedUser.getFirstName());
        userResponse.setLastName(savedUser.getLastName());
        userResponse.setCreatedAt(savedUser.getCreatedAt());
        userResponse.setUpdatedAt(savedUser.getUpdatedAt());
        return userResponse;

    }

}
