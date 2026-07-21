package com.example.dodast.Repository;

import com.example.dodast.Model.Conversation;
import com.example.dodast.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByConversationOrderBySentAtAsc(
            Conversation conversation
    );

}
