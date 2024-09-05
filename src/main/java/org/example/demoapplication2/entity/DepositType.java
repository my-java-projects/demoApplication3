package org.example.demoapplication2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String depositTools;

    @Column(unique = true, nullable = false)
    private Integer code;

    @OneToMany(mappedBy = "depositType")
    private List<Deposit> deposits = new ArrayList<>();

    // Constructor that generates a random code between 100 and 999
    public DepositType(String title, String depositTools) {
        this.title = title;
        this.depositTools = depositTools;
        this.code = generateCode();
    }

    private int generateCode() {
        return 100 + new Random().nextInt(900); // Generate a number between 100 and 999
    }
}
