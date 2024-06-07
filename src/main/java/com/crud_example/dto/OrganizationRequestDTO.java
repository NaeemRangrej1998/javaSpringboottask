package com.crud_example.dto;

import com.crud_example.entity.DTOEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationRequestDTO implements DTOEntity {
    private Long id;
    @NotNull
    @Size(min =4 , message = "Name should have atleast 2 characters")
    private String name;
    @NotNull
    @Size(min =5 ,max = 30,message = "Location  should have atleast 20 characters")
    private String location;
    @NotBlank
    @Email
    private String email;
    private String contactNumber;
}
