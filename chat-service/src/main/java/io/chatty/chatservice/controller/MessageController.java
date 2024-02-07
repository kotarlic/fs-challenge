package io.chatty.chatservice.controller;

import io.chatty.chatservice.dto.MessageDto;
import io.chatty.chatservice.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<List<MessageDto>> getMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @PostMapping
    public ResponseEntity<MessageDto> sendMessage(@RequestBody @Valid MessageDto messageDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(messageService.sendMessage(messageDto));
    }

}
