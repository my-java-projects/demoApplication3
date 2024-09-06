package org.example.demoapplication.controller;

import lombok.NoArgsConstructor;
import org.example.demoapplication.entity.DepositType;
import org.example.demoapplication.service.DepositTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/deposit-types")
@NoArgsConstructor
public class DepositTypeController {

    private DepositTypeService depositTypeService;


    public DepositTypeController(DepositTypeService depositTypeService) {
        this.depositTypeService = depositTypeService;
    }

    @PostMapping
    public ResponseEntity<String> createDepositType(@RequestBody DepositType depositType) {
        DepositType createdDepositType = depositTypeService.createDepositType(depositType);
        return ResponseEntity.ok("Deposit Type created with Code: " + createdDepositType.getCode());
    }

    @GetMapping("/{code}")
    public ResponseEntity<Optional<DepositType>> getDepositTypeByCode(@PathVariable Integer code) {
        Optional<DepositType> depositType = depositTypeService.getDepositTypeByCode(code);
        if (depositType.isPresent()) {
            return ResponseEntity.ok(depositType);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
