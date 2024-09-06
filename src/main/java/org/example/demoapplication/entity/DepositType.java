package org.example.demoapplication.entity;

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

    @Column(nullable = false)
    private String withdrawalTools;

    @Column(unique = true, nullable = false)
    private Integer code;

}
