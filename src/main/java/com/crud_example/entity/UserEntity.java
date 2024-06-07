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
@Table(name = "user")
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntityAudit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min =4 , message = "firstName should have atleast 2 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Size(min =4 , message = "lastName should have atleast 2 characters")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Email
    @Column(name = "email")
    private String email;

    @NotNull
    @Size(min =4 , message = "roleName should have atleast 2 characters")
    @Column(name = "role_name")
    private String roleName;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private OrganizationEntity organizationEntity;
}
