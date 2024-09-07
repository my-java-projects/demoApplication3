package org.example.demoapplication.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.demoapplication.dao.CustomerDAO;
import org.example.demoapplication.dao.DepositDAO;
import org.example.demoapplication.dao.DepositTypeDAO;
import org.example.demoapplication.entity.Customer;
import org.example.demoapplication.entity.Deposit;
import org.example.demoapplication.entity.DepositType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class DepositService {

    private final DepositDAO depositDAO;
    private final CustomerDAO customerDAO;
    private final DepositTypeDAO depositTypeDAO;

    @Autowired
    public DepositService(DepositDAO depositDAO, CustomerDAO customerDAO, DepositTypeDAO depositTypeDAO) {
        this.depositDAO = depositDAO;
        this.customerDAO = customerDAO;
        this.depositTypeDAO = depositTypeDAO;
    }

    public Deposit createDeposit(String customerNumber, Integer depositTypeCode, BigDecimal initialBalance) {
        Optional<Customer> customerOptional = customerDAO.findByCustomerNumber(customerNumber);
        if (customerOptional.isEmpty()) {
            throw new EntityNotFoundException("Customer not found");
        }

        Optional<DepositType> depositTypeOptional = depositTypeDAO.getDepositTypeByCode(depositTypeCode);
        if (depositTypeOptional.isEmpty()) {
            throw new EntityNotFoundException("Deposit Type not found");
        }

        Customer customer = customerOptional.get();
        DepositType depositType = depositTypeOptional.get();
        Deposit deposit = new Deposit(customer, depositType, initialBalance);

        depositDAO.saveDeposit(deposit);
        return deposit;
    }

    public Deposit getDeposit(String customerNumber, Integer depositTypeCode) {
        return depositDAO.getDepositByCustomerNumberAndDepositTypeCode(customerNumber, depositTypeCode);
    }

    public List<Deposit> getDepositsByCustomerNumber(String customerNumber) {
        return depositDAO.getDepositsByCustomerNumber(customerNumber);
    }
}
