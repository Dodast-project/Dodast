package com.example.dodast.Controller;

import com.example.dodast.DTO.Message.ConversationResponse;
import com.example.dodast.DTO.Message.MessageResponse;
import com.example.dodast.DTO.Message.SendMessageRequest;
import com.example.dodast.Service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public MessageResponse sendMessage(@Valid @RequestBody SendMessageRequest request) {
        return messageService.sendMessage(request);
    }

    @GetMapping("/conversations")
    public List<ConversationResponse> getConversations() {
        return messageService.getConversations();
    }

    @GetMapping("/conversations/{conversationId}")
    public List<MessageResponse> getMessages(@PathVariable Long conversationId) {
        return messageService.getMessages(conversationId);
    }
}
