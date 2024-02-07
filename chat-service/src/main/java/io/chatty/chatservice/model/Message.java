package io.chatty.chatservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Message {

    @Id
    private String id;

    private String text;

    private LocalDateTime createdAt;

    private String sender;

}
