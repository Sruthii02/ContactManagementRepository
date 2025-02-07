package com.example.contactapp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
}
