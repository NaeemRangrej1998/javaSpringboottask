package com.crud_example.dto;

import com.crud_example.entity.DTOEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationRequestDTO implements DTOEntity {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String location;
    private String email;
    private String contactNumber;
}
