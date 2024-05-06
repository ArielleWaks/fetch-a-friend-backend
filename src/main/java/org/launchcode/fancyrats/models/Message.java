package org.launchcode.fancyrats.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String senderName;
    @Column
    private String receiverName;
    @Column
    private String message;
    @Column
    private String date;
    @Enumerated(EnumType.STRING)
    private Status status;
}
