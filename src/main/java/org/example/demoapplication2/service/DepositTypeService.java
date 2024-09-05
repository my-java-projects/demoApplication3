package org.example.demoapplication2.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.demoapplication2.entity.DepositType;
import org.example.demoapplication2.repository.DepositTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class DepositTypeService {

    private DepositTypeRepository depositTypeRepository;

    @Autowired
    public DepositTypeService(DepositTypeRepository depositTypeRepository) {
        this.depositTypeRepository = depositTypeRepository;
    }

    public DepositType createDepositType(DepositType depositType) {
        if (depositType.getCode() == null) {
            depositType.setCode(generateCode());
        }
        // Ensure unique code generation
        while (depositTypeRepository.existsByCode(depositType.getCode())) {
            depositType.setCode(generateCode());
        }
        return depositTypeRepository.save(depositType);
    }

    public Optional<DepositType> getDepositTypeById(Long id) {
        return depositTypeRepository.findById(id);
    }

    private int generateCode() {
        return 100 + (int)(Math.random() * 900); // Generates a code between 100 and 999
    }
}

