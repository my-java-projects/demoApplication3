package org.example.demoapplication.service;

import lombok.NoArgsConstructor;
import org.example.demoapplication.dao.DepositTypeDAO;
import org.example.demoapplication.entity.DepositType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.Random;

@Service
@NoArgsConstructor
public class DepositTypeService {

    @Autowired
    private DepositTypeDAO depositTypeDAO;

    public DepositType createDepositType(DepositType depositType) {
    /* if (depositType.getCode() == null) {
            depositType.setCode(generateCode());
        }*/
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
        while (depositTypeDAO.getDepositTypeByCode(code) != null) {
            code = random.nextInt(900) + 100; // Ensure the code is unique
        }
        return code;
    }
}



