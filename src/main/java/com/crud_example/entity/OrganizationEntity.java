package com.crud_example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "organization")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationEntity extends BaseEntityAudit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min =4 , message = "First Name should have atleast 2 characters")
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(min =5 ,max = 30,message = "First Name should have atleast 2 characters")
    @Column(name = "location")
    private String location;

    @NotBlank
    @Email
    @Size(min =4 , message = "First Name should have atleast 2 characters")
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "contact_number")
    private String contactNumber;
}
