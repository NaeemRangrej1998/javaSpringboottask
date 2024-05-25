package com.crud_example.dto;

import com.crud_example.entity.DTOEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationResponseDTO implements DTOEntity {

    private Long id;
    private String name;
    private String location;
    private String email;
    private String contactNumber;
    private Boolean status;
    private Boolean deactivate;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;


}
