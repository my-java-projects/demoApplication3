package org.example.demoapplication2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data // Lombok will generate getters, setters, toString, equals, and hashcode
@NoArgsConstructor // Lombok will generate a no-arg constructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nationalId;

    private String firstName;
    private String lastName;
    private String fathersName;
    private String cityOfBirth;
    private String phoneNumber;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Deposit> deposits = new ArrayList<>();
}
