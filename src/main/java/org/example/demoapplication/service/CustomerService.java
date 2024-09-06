package org.example.demoapplication.service;

import lombok.NoArgsConstructor;
import org.example.demoapplication.dao.CustomerDAO;
import org.example.demoapplication.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@NoArgsConstructor
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Transactional
    public String createCustomer(Customer customer) {
        // Generate a unique customer number
        String customerNumber = UUID.randomUUID().toString().substring(0, 8);  // 8-character unique string
        customer.setCustomerNumber(customerNumber);

        // Save the customer
        customerDAO.saveCustomer(customer);

        return customerNumber;  // Return the generated customer number
    }

    @Transactional
    public Optional<Customer> findByNationalId(String nationalId) {
        return customerDAO.findByNationalId(nationalId);
    }

}
