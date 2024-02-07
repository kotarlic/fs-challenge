package io.chatty.chatservice.service;

import io.chatty.chatservice.dto.UserDto;
import io.chatty.chatservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getOrCreateUser_returnUser_whenUsernameIsProvided() {
        // Given
        UserDto testUser = UserDto.builder().username("Test user").build();
        when(userRepository.findFirstByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser.covertToModel()));

        // When
        UserDto expectedUserDto = userService.getOrCreateUser(testUser);

        // Then
        assertEquals(testUser.getUsername(), expectedUserDto.getUsername());
    }

}
