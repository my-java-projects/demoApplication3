package org.example.demoapplication2.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.demoapplication2.entity.Customer;
import org.example.demoapplication2.entity.Deposit;
import org.example.demoapplication2.entity.DepositType;
import org.example.demoapplication2.repository.CustomerRepository;
import org.example.demoapplication2.repository.DepositRepository;
import org.example.demoapplication2.repository.DepositTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class DepositService {

    private DepositRepository depositRepository;

    private CustomerRepository customerRepository;

    private DepositTypeRepository depositTypeRepository;

    @Autowired
    public DepositService(DepositRepository depositRepository, CustomerRepository customerRepository, DepositTypeRepository depositTypeRepository) {
        this.depositRepository = depositRepository;
        this.customerRepository = customerRepository;
        this.depositTypeRepository = depositTypeRepository;
    }

    public Deposit createDeposit(Long customerId, Long depositTypeId, BigDecimal initialBalance) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            throw new EntityNotFoundException("Customer not found");
        }

        Optional<DepositType> depositType = depositTypeRepository.findById(depositTypeId);
        if (depositType.isEmpty()) {
            throw new EntityNotFoundException("Deposit Type not found");
        }

        Deposit deposit = new Deposit(customer.get(), depositType.get(), initialBalance);
        return depositRepository.save(deposit);
    }

    public List<Deposit> getCustomerDeposits(Customer customer) {
        return depositRepository.findByCustomer(customer);
    }
}
