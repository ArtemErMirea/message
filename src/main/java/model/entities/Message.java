package model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "messages")
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User fromUser;

    @ManyToOne
    private User toUser;

    @Column(name = "message_headline", nullable = false)
    private String messageHeadline;

    @Column(name = "message_text", nullable = false)
    private String messageText;

    @Column(name = "send_date", nullable = false)
    private Timestamp sendDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MessageStatus status;
}
