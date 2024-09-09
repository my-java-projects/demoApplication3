package org.example.demoapplication.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
//@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositType {

    @Id
    @Access(AccessType.PROPERTY) // Access the ID through the getter method
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String withdrawalTools;

    @Column(unique = true, nullable = false)
    private Integer code;

    // One deposit type can be used by multiple deposits
    @OneToMany(mappedBy = "depositType",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Deposit> deposits;


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getWithdrawalTools() {
        return withdrawalTools;
    }

    public Integer getCode() {
        return code;
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWithdrawalTools(String withdrawalTools) {
        this.withdrawalTools = withdrawalTools;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }
}
