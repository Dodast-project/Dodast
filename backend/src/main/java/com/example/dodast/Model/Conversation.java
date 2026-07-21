package com.example.dodast.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "conversations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @ManyToOne
    @JoinColumn(name = "advertisement_id", nullable = false)
    private Advertisement advertisement;

    private LocalDateTime createdAt;

    private LocalDateTime lastMessageAt;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Message> messages = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        lastMessageAt = createdAt;
    }
}
