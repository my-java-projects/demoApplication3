package org.example.demoapplication2.repository;

import org.example.demoapplication2.entity.DepositType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositTypeRepository extends JpaRepository<DepositType, Long> {
    boolean existsByCode(Integer code);
}