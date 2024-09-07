package org.example.demoapplication.service;

import org.example.demoapplication.dao.DepositTypeDAO;
import org.example.demoapplication.entity.DepositType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class DepositTypeService {

    private final DepositTypeDAO depositTypeDAO;

    // Constructor-based dependency injection
    @Autowired
    public DepositTypeService(DepositTypeDAO depositTypeDAO) {
        this.depositTypeDAO = depositTypeDAO;
    }

    public DepositType createDepositType(DepositType depositType) {
        depositType.setCode(generateUniqueCode());
        depositTypeDAO.saveDepositType(depositType);
        return depositType;
    }

    public Optional<DepositType> getDepositTypeByCode(Integer code) {
        return depositTypeDAO.getDepositTypeByCode(code);
    }

    private int generateUniqueCode() {
        Random random = new Random();
        int code = random.nextInt(900) + 100; // Generates a code between 100 and 999
        while (depositTypeDAO.getDepositTypeByCode(code).isPresent()) {
            code = random.nextInt(900) + 100; // Ensure the code is unique
        }
        return code;
    }
}
