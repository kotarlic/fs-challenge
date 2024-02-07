package io.chatty.chatservice.dto;

import io.chatty.chatservice.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank
    private String username;

    public static UserDto convertToDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .build();
    }

    public User covertToModel() {
        return User.builder()
                .username(getUsername())
                .build();
    }

}
