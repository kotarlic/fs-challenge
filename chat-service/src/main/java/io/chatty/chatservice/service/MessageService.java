package io.chatty.chatservice.service;

import io.chatty.chatservice.dto.MessageDto;
import io.chatty.chatservice.exceptions.NotFoundException;
import io.chatty.chatservice.repository.MessageRepository;
import io.chatty.chatservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    private final UserRepository userRepository;

    private final SimpMessagingTemplate simpMessagingTemplate;

    public List<MessageDto> getAllMessages() {
        return messageRepository.findAll(Sort.by(Sort.Direction.ASC, "createdAt"))
                .stream().map(MessageDto::convertToDto).toList();
    }

    public MessageDto sendMessage(MessageDto messageDto) {
        userRepository.findFirstByUsername(messageDto.getSender())
                .orElseThrow(() -> new NotFoundException(String.format("Sender with username %s not found", messageDto.getSender())));
        MessageDto message = MessageDto.convertToDto(messageRepository.insert(messageDto.convertToModel()));
        simpMessagingTemplate.convertAndSend("/api/messages", message);

        return message;
    }

}

