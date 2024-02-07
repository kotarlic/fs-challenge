package io.chatty.chatservice.controller;

import io.chatty.chatservice.dto.UserDto;
import io.chatty.chatservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> getOrCreateUser(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(userService.getOrCreateUser(userDto));
    }

}
