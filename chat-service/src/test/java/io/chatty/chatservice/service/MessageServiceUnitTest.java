package io.chatty.chatservice.service;

import io.chatty.chatservice.dto.MessageDto;
import io.chatty.chatservice.dto.UserDto;
import io.chatty.chatservice.model.Message;
import io.chatty.chatservice.repository.MessageRepository;
import io.chatty.chatservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MessageServiceUnitTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SimpMessagingTemplate simpMessagingTemplate;

    @InjectMocks
    private MessageService messageService;

    @Test
    void getAllMessages_returnMessages_whenMessagesExist() {
        // Given
        MessageDto testMessage = createMessageDto();
        when(messageRepository.findAll(Sort.by(Sort.Direction.ASC, "createdAt")))
                .thenReturn(List.of(testMessage.convertToModel()));

        // When
        List<MessageDto> expected = messageService.getAllMessages();

        // Then
        assertEquals(expected.get(0).getSender(), testMessage.getSender());
        assertEquals(expected.get(0).getText(), testMessage.getText());
        assertEquals(expected.get(0).getCreatedAt(), testMessage.getCreatedAt());
    }

    @Test
    void getAllMessages_returnEmptyListOfMessages_whenMessagesDoNotExist() {
        // Given
        when(messageRepository.findAll(Sort.by(Sort.Direction.ASC, "createdAt")))
                .thenReturn(List.of());

        // When
        List<MessageDto> expected = messageService.getAllMessages();

        // Then
        assertTrue(expected.isEmpty());
    }

    @Test
    void sendMessage_storeAndSendMessage_whenMessageIsProvided() {
        // Given
        MessageDto testMessage = createMessageDto();
        Message message = testMessage.convertToModel();

        when(userRepository.findFirstByUsername(anyString()))
                .thenReturn(Optional.of(createUserDto().covertToModel()));
        when(messageRepository.insert((Message) any())).thenReturn(message);
        doNothing().when(simpMessagingTemplate).convertAndSend(anyString(), Optional.ofNullable(any()));

        // When
        MessageDto expected = messageService.sendMessage(testMessage);

        // Then
        assertEquals(expected.getSender(), testMessage.getSender());
        assertEquals(expected.getText(), testMessage.getText());
        assertEquals(expected.getCreatedAt(), testMessage.getCreatedAt());
    }

    private UserDto createUserDto() {
        return UserDto.builder()
                .username("Test user")
                .build();
    }

    private MessageDto createMessageDto() {
        return MessageDto.builder()
                .sender("Test user")
                .text("Test message")
                .createdAt(LocalDateTime.now())
                .build();
    }

}
