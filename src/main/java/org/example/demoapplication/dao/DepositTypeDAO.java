package org.example.demoapplication.dao;

import org.example.demoapplication.entity.DepositType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Optional;

public class DepositTypeDAO {

    private final SessionFactory sessionFactory;

    public DepositTypeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Save or update DepositType
    public void saveDepositType(DepositType depositType) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(depositType);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Get DepositType by code
    public Optional<DepositType> getDepositTypeByCode(Integer code) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "from DepositType where code = :code";
            Query<DepositType> query = session.createQuery(hql, DepositType.class);
            query.setParameter("code", code);
            DepositType depositType = query.uniqueResult();
            transaction.commit();
            return Optional.ofNullable(depositType);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return Optional.empty();
        } finally {
            session.close();
        }
    }
}
