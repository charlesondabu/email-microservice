package com.condabu.email.model;

import jakarta.persistence.Column;

import java.io.Serializable;

public class ContactGroupId implements Serializable {
    @Column(
            name = "contact_id"
    )
    private Long contactId;
    @Column(
            name = "group_id"
    )
    private Long groupId;
}
