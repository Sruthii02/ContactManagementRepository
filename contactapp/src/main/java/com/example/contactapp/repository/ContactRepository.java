package com.example.contactapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.contactapp.model.Contact;

@Repository

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
