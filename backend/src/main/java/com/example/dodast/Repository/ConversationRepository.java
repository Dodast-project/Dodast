package com.example.dodast.Repository;

import com.example.dodast.Model.Advertisement;
import com.example.dodast.Model.Conversation;
import com.example.dodast.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    Optional<Conversation> findByBuyerAndSellerAndAdvertisement(
            User buyer,
            User seller,
            Advertisement advertisement
    );

    List<Conversation> findByBuyerOrSellerOrderByLastMessageAtDesc(
            User buyer,
            User seller
    );

}
