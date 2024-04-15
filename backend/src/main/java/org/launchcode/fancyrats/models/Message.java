package org.launchcode.fancyrats.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {

    private String senderName;

    private String recieverName;

    private String content;

    private String timestamp;

    private Status status;
}
