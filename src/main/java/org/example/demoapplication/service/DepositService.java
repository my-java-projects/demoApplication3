package org.example.demoapplication.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.demoapplication.dao.CustomerDAO;
import org.example.demoapplication.dao.DepositDAO;
import org.example.demoapplication.dao.DepositTypeDAO;
import org.example.demoapplication.entity.Customer;
import org.example.demoapplication.entity.Deposit;
import org.example.demoapplication.entity.DepositType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class DepositService {

    private final DepositDAO depositDAO;
    private final CustomerDAO customerDAO;
    private final DepositTypeDAO depositTypeDAO;

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
