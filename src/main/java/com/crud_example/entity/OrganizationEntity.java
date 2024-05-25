package com.crud_example.entity;

import jakarta.persistence.*;
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

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "email")
    private String email;

    @Column(name = "contact_number")
    private String contactNumber;
}
