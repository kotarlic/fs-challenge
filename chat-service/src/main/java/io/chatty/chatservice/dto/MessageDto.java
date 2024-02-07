package io.chatty.chatservice.dto;

import io.chatty.chatservice.model.Message;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    @NotBlank
    private String text;

    private LocalDateTime createdAt;

    @NotBlank
    private String sender;

    public static MessageDto convertToDto(Message message) {
        return MessageDto.builder()
                .text(message.getText())
                .createdAt(message.getCreatedAt())
                .sender(message.getSender())
                .build();
    }

    public Message convertToModel() {
        return Message.builder()
                .text(getText())
                .createdAt(getCreatedAt())
                .sender(getSender())
                .build();
    }

}
