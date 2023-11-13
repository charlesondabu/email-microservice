package com.condabu.email.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "email_messages"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailMessage {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            name = "target_group"
    )
    private String group;
    private String subject;
    @Column(
            name = "message_body",
            columnDefinition = "TEXT"
    )
    private String message;
    private String status;
}
