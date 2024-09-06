package org.example.demoapplication.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.demoapplication.entity.Customer;
import org.example.demoapplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@NoArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        String customerNumber = customerService.createCustomer(customer);
        return ResponseEntity.ok("Customer created with unique customer number: " + customerNumber);  // Return unique customer number
    }

    @GetMapping("/{nationalId}")
    public ResponseEntity<Customer> getCustomerByNationalId(@PathVariable String nationalId) {
        return customerService.findByNationalId(nationalId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body(null));
    }
}
