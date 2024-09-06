package org.example.demoapplication.dao;


import org.example.demoapplication.entity.Deposit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepositDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void saveDeposit(Deposit deposit) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(deposit);
    }

    public Deposit getDepositByCustomerNumberAndDepositTypeCode(String customerNumber, Integer depositTypeCode) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(
                        "select d from Deposit d join d.customer c join d.depositType dt where c.customerNumber = :customerNumber and dt.code = :depositTypeCode",
                        Deposit.class
                )
                .setParameter("customerNumber", customerNumber)
                .setParameter("depositTypeCode", depositTypeCode)
                .uniqueResult();
    }


    public List<Deposit> getDepositsByCustomerNumber(String customerNumber) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(
                        "select d from Deposit d join d.customer c where c.customerNumber = :customerNumber",
                        Deposit.class
                )
                .setParameter("customerNumber", customerNumber)
                .getResultList();
    }
}
