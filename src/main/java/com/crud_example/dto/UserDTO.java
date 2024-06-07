package com.crud_example.dto;

import com.crud_example.entity.DTOEntity;
import com.crud_example.entity.OrganizationEntity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements DTOEntity {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String roleName;
    private Long organization;
}
