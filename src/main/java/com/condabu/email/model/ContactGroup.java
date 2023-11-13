package com.condabu.email.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
        name = "contact_group"
)
@Getter
@Setter
public class ContactGroup {
    @EmbeddedId
    private ContactGroupId id;
    @ManyToOne
    @JoinColumn(
            name = "contact_id",
            insertable = false,
            updatable = false
    )
    private Contact contact;
    @ManyToOne
    @JoinColumn(
            name = "group_id",
            insertable = false,
            updatable = false
    )
    private Group group;
    @Column(
            name = "send_to"
    )
    private boolean sendTo;

}
