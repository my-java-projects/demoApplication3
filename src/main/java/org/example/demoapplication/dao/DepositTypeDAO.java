package org.example.demoapplication.dao;

import org.example.demoapplication.entity.DepositType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DepositTypeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void saveDepositType(DepositType depositType) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(depositType);
    }

    public Optional<DepositType> getDepositTypeByCode(Integer code) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from DepositType where code = :code", DepositType.class)
                .setParameter("code", code)
                .uniqueResultOptional();
    }
}