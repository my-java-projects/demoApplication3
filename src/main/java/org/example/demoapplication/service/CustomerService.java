package org.example.demoapplication.service;

import lombok.NoArgsConstructor;
import org.example.demoapplication.dao.CustomerDAO;
import org.example.demoapplication.entity.Customer;

import java.util.Optional;
import java.util.UUID;

//@NoArgsConstructor
public class CustomerService {

    private final CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public String createCustomer(Customer customer) {
        // Generate a unique customer number
        String customerNumber = UUID.randomUUID().toString().substring(0, 8);  // 8-character unique string
        customer.setCustomerNumber(customerNumber);

        // Save the customer - manually handled within CustomerDAO
        customerDAO.saveCustomer(customer);

        return customerNumber;  // Return the generated customer number
    }

    public Optional<Customer> findByNationalId(String nationalId) {
        return customerDAO.findByNationalId(nationalId);
    }
}
