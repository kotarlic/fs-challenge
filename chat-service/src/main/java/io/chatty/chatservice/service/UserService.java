package io.chatty.chatservice.service;

import io.chatty.chatservice.dto.UserDto;
import io.chatty.chatservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto getOrCreateUser(UserDto userDto) {
        return UserDto.convertToDto(userRepository.findFirstByUsername(userDto.getUsername())
                .orElse(userRepository.insert(userDto.covertToModel())));
    }

}
