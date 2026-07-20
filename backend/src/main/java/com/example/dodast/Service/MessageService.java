package com.example.dodast.Service;

import com.example.dodast.DTO.Message.ConversationResponse;
import com.example.dodast.DTO.Message.MessageResponse;
import com.example.dodast.DTO.Message.SendMessageRequest;
import com.example.dodast.Exception.AdvertisementNotFoundException;
import com.example.dodast.Exception.ConversationAccessDeniedException;
import com.example.dodast.Exception.ConversationNotFoundException;
import com.example.dodast.Exception.SelfMessageException;
import com.example.dodast.Model.Advertisement;
import com.example.dodast.Model.Conversation;
import com.example.dodast.Model.Message;
import com.example.dodast.Model.User;
import com.example.dodast.Repository.AdvertisementRepository;
import com.example.dodast.Repository.ConversationRepository;
import com.example.dodast.Repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final AdvertisementRepository advertisementRepository;

    @Transactional
    public MessageResponse sendMessage(SendMessageRequest request) {

        Advertisement advertisement = advertisementRepository.findById(request.getAdvertisementId())
                .orElseThrow(AdvertisementNotFoundException::new);

        User currentUser = AdAuthenticator.getCurrentUser();
        User seller = advertisement.getOwner();

        if (currentUser.getId().equals(seller.getId())) {
            throw new SelfMessageException();
        }

        Conversation conversation = conversationRepository
                .findByBuyerAndSellerAndAdvertisement(currentUser, seller, advertisement)
                .orElseGet(() -> conversationRepository.save(
                        Conversation.builder()
                                .buyer(currentUser)
                                .seller(seller)
                                .advertisement(advertisement)
                                .build()
                ));

        Message message = Message.builder()
                .conversation(conversation)
                .sender(currentUser)
                .text(request.getText())
                .build();

        messageRepository.save(message);

        conversation.setLastMessageAt(LocalDateTime.now());
        conversationRepository.save(conversation);

        return new MessageResponse(
                message.getId(),
                currentUser.getId(),
                currentUser.getFullName(),
                message.getText(),
                message.getSentAt()
        );
    }

    @Transactional(readOnly = true)
    public List<ConversationResponse> getConversations() {

        User currentUser = AdAuthenticator.getCurrentUser();

        List<Conversation> conversations = conversationRepository
                .findByBuyerOrSellerOrderByLastMessageAtDesc(currentUser, currentUser);

        List<ConversationResponse> result = new ArrayList<>();

        for (Conversation conversation : conversations) {

            User otherUser = conversation.getBuyer().getId().equals(currentUser.getId())
                    ? conversation.getSeller()
                    : conversation.getBuyer();

            List<Message> messages = messageRepository.findByConversationOrderBySentAtAsc(conversation);
            String lastMessage = messages.isEmpty() ? null : messages.get(messages.size() - 1).getText();

            result.add(new ConversationResponse(
                    conversation.getId(),
                    conversation.getAdvertisement().getId(),
                    conversation.getAdvertisement().getTitle(),
                    otherUser.getFullName(),
                    lastMessage,
                    conversation.getLastMessageAt()
            ));
        }

        return result;
    }

    @Transactional(readOnly = true)
    public List<MessageResponse> getMessages(Long conversationId) {

        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(ConversationNotFoundException::new);

        User currentUser = AdAuthenticator.getCurrentUser();

        checkParticipant(conversation, currentUser);

        List<Message> messages = messageRepository.findByConversationOrderBySentAtAsc(conversation);

        List<MessageResponse> result = new ArrayList<>();

        for (Message message : messages) {
            result.add(new MessageResponse(
                    message.getId(),
                    message.getSender().getId(),
                    message.getSender().getFullName(),
                    message.getText(),
                    message.getSentAt()
            ));
        }

        return result;
    }

    private void checkParticipant(Conversation conversation, User user) {
        boolean isBuyer = conversation.getBuyer().getId().equals(user.getId());
        boolean isSeller = conversation.getSeller().getId().equals(user.getId());

        if (!isBuyer && !isSeller) {
            throw new ConversationAccessDeniedException();
        }
    }
}
