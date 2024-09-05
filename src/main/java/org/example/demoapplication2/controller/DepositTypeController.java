package org.example.demoapplication2.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.demoapplication2.entity.DepositType;
import org.example.demoapplication2.service.DepositTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deposit-types")
@NoArgsConstructor
public class DepositTypeController {

    private DepositTypeService depositTypeService;

    @Autowired
    public DepositTypeController(DepositTypeService depositTypeService) {
        this.depositTypeService = depositTypeService;
    }

    @PostMapping
    public ResponseEntity<String> createDepositType(@RequestBody DepositType depositType) {
        DepositType createdDepositType = depositTypeService.createDepositType(depositType);
        return ResponseEntity.ok("Deposit Type created with Code: " + createdDepositType.getCode());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepositType> getDepositTypeById(@PathVariable Long id) {
        return depositTypeService.getDepositTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body(null));
    }
}
