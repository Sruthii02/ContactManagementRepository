package com.example.contactapp.controller;

import com.example.contactapp.ContactappApplicationTests;
import com.example.contactapp.model.Contact;
import com.example.contactapp.repository.ContactRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContactappApplicationTests

public class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ContactRepository contactRepository;

    private Contact contact;

    @BeforeEach
    void setup() {
        contact = new Contact(1L, "Sruthi", "sruthi@123.com", "123456789");
    }

    @Test
    void testGetAllContacts() throws Exception {
        when(contactRepository.findAll()).thenReturn(Arrays.asList(contact));

        mockMvc.perform(get("/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Sruthi"));
    }

    @Test
    void testGetContactById() throws Exception {
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));

        mockMvc.perform(get("/contacts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sruthi"));
    }

    @Test
    @WithMockUser(username = "user", roles = { "USER" })
    void testCreateContact() throws Exception {
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);

        mockMvc.perform(post("/contacts/create")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(contact)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Sruthi"));
    }

    @Test
    void testUpdateContact() throws Exception {
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);

        mockMvc.perform(put("/contacts/1")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(contact)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sruthi"));
    }

    @Test
    void testDeleteContact() throws Exception {
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));
        Mockito.doNothing().when(contactRepository).deleteById(1L);

        mockMvc.perform(delete("/contacts/1")
                .with(csrf()))
                .andExpect(status().isNoContent());
    }
}
