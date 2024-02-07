package io.chatty.chatservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.chatty.chatservice.dto.MessageDto;
import io.chatty.chatservice.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MessageController.class)
public class MessageControllerUnitTest {

    private final String RESOURCE_ENDPOINT = "/api/messages";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MessageService messageService;

    @Test
    void getAllMessages_returnOkAndMessages_whenMessagesExist() throws Exception {
        // Given
        MessageDto testMessage = createMessageDto();

        when(messageService.getAllMessages()).thenReturn(List.of(testMessage));

        // When & Then
        mockMvc.perform(get(RESOURCE_ENDPOINT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].sender").value(testMessage.getSender()))
                .andExpect(jsonPath("$.[0].text").value(testMessage.getText()));
    }

    @Test
    void getAllMessages_returnOkAndEmptyListOfMessages_whenMessagesDoNotExist() throws Exception {
        // Given
        when(messageService.getAllMessages()).thenReturn(List.of());

        // When & Then
        mockMvc.perform(get(RESOURCE_ENDPOINT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(0));
    }

    @Test
    void sendMessage_returnCreatedStoreAndSendMessage_whenMessageIsProvided() throws Exception {
        // Given
        MessageDto testMessage = createMessageDto();

        when(messageService.sendMessage(any())).thenReturn(testMessage);

        // When & Then
        mockMvc.perform(post(RESOURCE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testMessage)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.sender").value(testMessage.getSender()))
                .andExpect(jsonPath("$.text").value(testMessage.getText()));
    }

    private MessageDto createMessageDto() {
        return MessageDto.builder()
                .sender("Test user")
                .text("Test message")
                .createdAt(LocalDateTime.now())
                .build();
    }
}
