package org.example.demoapplication.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
//@Data // Lombok will generate getters, setters, toString, equals, and hashcode
@NoArgsConstructor // Lombok will generate a no-arg constructor
@AllArgsConstructor
public class Customer {

    @Id
    @Access(AccessType.PROPERTY) // Access the ID through the getter method
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nationalId;

    @Column(unique = true, nullable = false)
    private String customerNumber;

    private String firstName;
    private String lastName;
    private String fathersName;
    private String cityOfBirth;
    private String phoneNumber;


    // One customer can have multiple deposits
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Deposit> deposits;


    public Long getId() {
        return id;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFathersName() {
        return fathersName;
    }

    public String getCityOfBirth() {
        return cityOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public void setCityOfBirth(String cityOfBirth) {
        this.cityOfBirth = cityOfBirth;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }
}
