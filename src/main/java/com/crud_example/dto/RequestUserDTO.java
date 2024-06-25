package com.crud_example.dto;

import com.crud_example.entity.DTOEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestUserDTO implements DTOEntity {

    private Long id;
    private String firstName;

    private String lastName;

    private String email;

    private String roleName;

    private Long organization;
}
