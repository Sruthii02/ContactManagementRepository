package com.example.contactapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.contactapp.dto.ContactDTO;
import com.example.contactapp.model.Contact;
import com.example.contactapp.repository.ContactRepository;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ContactDTO createContact(ContactDTO contactDTO) {
        Contact contact = new Contact();
        contact.setName(contactDTO.getName());
        contact.setEmail(contactDTO.getEmail());
        contact.setPhone(contactDTO.getPhone());
        Contact savedContact = contactRepository.save(contact);
        return convertToDTO(savedContact);
    }

    public List<ContactDTO> getAllContacts() {
        return contactRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ContactDTO getContactById(Long id) {
        return contactRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Contact not found"));
    }

    public ContactDTO updateContact(Long id, ContactDTO contactDTO) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new RuntimeException("Contact Not Found"));
        contact.setName(contactDTO.getName());
        contact.setEmail(contactDTO.getEmail());
        contact.setPhone(contactDTO.getPhone());
        Contact savedContact = contactRepository.save(contact);
        return convertToDTO(savedContact);
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }

    public ContactDTO convertToDTO(Contact contact) {
        return new ContactDTO(contact.getId(), contact.getName(), contact.getEmail(), contact.getPhone());
    }
}
