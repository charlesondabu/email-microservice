package com.condabu.email.repository;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower dec

import com.condabu.email.model.ContactGroup;
import com.condabu.email.model.ContactGroupId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactGroupRepository extends JpaRepository<ContactGroup, ContactGroupId> {
    List<ContactGroup> findByGroupGroupNameAndSendTo(String groupName, boolean sendTo);
}
