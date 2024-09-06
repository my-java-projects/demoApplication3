package org.example.demoapplication.dao;

import org.example.demoapplication.entity.Deposit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DepositDAO {

    private final SessionFactory sessionFactory;

    public DepositDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Save or update deposit
    public void saveDeposit(Deposit deposit) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(deposit);
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

    // Get deposit by customer number and deposit type code
    public Deposit getDepositByCustomerNumberAndDepositTypeCode(String customerNumber, Integer depositTypeCode) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "select d from Deposit d join d.customer c join d.depositType dt where c.customerNumber = :customerNumber and dt.code = :depositTypeCode";
            Query<Deposit> query = session.createQuery(hql, Deposit.class);
            query.setParameter("customerNumber", customerNumber);
            query.setParameter("depositTypeCode", depositTypeCode);
            Deposit deposit = query.uniqueResult();
            transaction.commit();
            return deposit;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    // Get deposits by customer number
    public List<Deposit> getDepositsByCustomerNumber(String customerNumber) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "select d from Deposit d join d.customer c where c.customerNumber = :customerNumber";
            Query<Deposit> query = session.createQuery(hql, Deposit.class);
            query.setParameter("customerNumber", customerNumber);
            List<Deposit> deposits = query.getResultList();
            transaction.commit();
            return deposits;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return List.of();
        } finally {
            session.close();
        }
    }
}
