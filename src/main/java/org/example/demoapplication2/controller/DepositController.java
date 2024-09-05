package org.example.demoapplication2.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.demoapplication2.entity.Deposit;
import org.example.demoapplication2.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/deposits")
@NoArgsConstructor
public class DepositController {

    private DepositService depositService;

    @Autowired
    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @PostMapping
    public ResponseEntity<String> createDeposit(@RequestParam Long customerId,
                                                @RequestParam Long depositTypeId,
                                                @RequestParam BigDecimal initialBalance) {
        Deposit deposit = depositService.createDeposit(customerId, depositTypeId, initialBalance);
        return ResponseEntity.ok("Deposit created with opening date: " + deposit.getOpeningDate());
    }
}
