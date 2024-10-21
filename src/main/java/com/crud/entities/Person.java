package com.crud.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persons")
public class Person {

    // Table Columns
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, length = 15)
    private String name;

    @Column(name = "last_name", nullable = false, length = 15)
    private String lastName;

    @Column(name = "email", nullable = false, length = 50)
    private String email;
}
