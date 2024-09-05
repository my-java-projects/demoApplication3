package org.example.demoapplication2.repository;

import org.example.demoapplication2.entity.Customer;
import org.example.demoapplication2.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {
    List<Deposit> findByCustomer(Customer customer);
}