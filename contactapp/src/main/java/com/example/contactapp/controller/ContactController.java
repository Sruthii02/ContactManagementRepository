package com.example.contactapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.contactapp.dto.ContactDTO;

import com.example.contactapp.service.ContactService;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired(required = true)
    private ContactService contactService;

    @PostMapping("/create")
    public ResponseEntity<Object> createContact(@RequestBody ContactDTO contactDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contactService.createContact(contactDTO));
    }

    @GetMapping
    public List<ContactDTO> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/{id}")
    public ContactDTO getContactById(@PathVariable Long id) {
        return contactService.getContactById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateContact(@PathVariable Long id, @RequestBody ContactDTO contactDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(contactService.updateContact(id, contactDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
