package org.example.demoapplication.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
//@Data
@NoArgsConstructor
//@AllArgsConstructor
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.PROPERTY) // Access the ID through the getter method
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "deposit_type_id", nullable = false)
    @JsonBackReference
    private DepositType depositType;

    private BigDecimal initialBalance;   // BigDecimal for monetary values
    private BigDecimal withdrawableBalance;
    private LocalDateTime openingDate;

    public Deposit(Customer customer, DepositType depositType, BigDecimal initialBalance) {
        this.customer = customer;
        this.depositType = depositType;
        this.initialBalance = initialBalance;
        this.withdrawableBalance = initialBalance;
        this.openingDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public DepositType getDepositType() {
        return depositType;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public BigDecimal getWithdrawableBalance() {
        return withdrawableBalance;
    }

    public LocalDateTime getOpeningDate() {
        return openingDate;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDepositType(DepositType depositType) {
        this.depositType = depositType;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public void setWithdrawableBalance(BigDecimal withdrawableBalance) {
        this.withdrawableBalance = withdrawableBalance;
    }

    public void setOpeningDate(LocalDateTime openingDate) {
        this.openingDate = openingDate;
    }
}
