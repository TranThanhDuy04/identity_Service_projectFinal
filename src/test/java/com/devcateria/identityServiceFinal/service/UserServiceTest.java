package com.devcateria.identityServiceFinal.service;

import com.devcateria.identityServiceFinal.dto.request.UserCreationRequest;
import com.devcateria.identityServiceFinal.dto.response.UserResponse;
import com.devcateria.identityServiceFinal.entity.User;
import com.devcateria.identityServiceFinal.exception.AppExeption;
import com.devcateria.identityServiceFinal.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private UserCreationRequest request;
    private UserResponse userResponse;
    private User user;
    private LocalDate dob;

    @BeforeEach
    void initData(){
        dob = LocalDate.of(1990, 1,1);

        request = UserCreationRequest.builder()
                .username("john")
                .firstName("john")
                .lastName("Doe")
                .password("12345678")
                .dob(dob)
                .build();

        userResponse = UserResponse.builder()
                .id("8ed35c14b2a8")
                .username("john")
                .firstName("john")
                .lastName("Doe")
                .dob(dob)
                .build();

        user = User.builder()
                .id("8ed35c14b2a8")
                .username("john")
                .firstName("john")
                .lastName("Doe")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success(){
        //given
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        //when
        var response = userService.createUser(request);

        //then
        Assertions.assertThat(response.getId()).isEqualTo("8ed35c14b2a8");
        Assertions.assertThat(response.getUsername()).isEqualTo("john");

    }

    @Test
    void createUser_UserExistd_fail(){
        //given
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        //when
       var exception = assertThrows(AppExeption.class,() -> userService.createUser(request));
        //then
       Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1002);

    }
}
