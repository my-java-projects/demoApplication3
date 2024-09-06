package org.example.demoapplication.dao;

import org.example.demoapplication.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public class CustomerDAO {

    private final SessionFactory sessionFactory;

    public CustomerDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Save or update customer
    public void saveCustomer(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(customer);
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

    // Find customer by customerNumber
    public Optional<Customer> findByCustomerNumber(String customerNumber) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "FROM Customer WHERE customerNumber = :customerNumber";
            Query<Customer> query = session.createQuery(hql, Customer.class);
            query.setParameter("customerNumber", customerNumber);
            Customer result = query.uniqueResult();
            transaction.commit();
            return Optional.ofNullable(result);
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

    // Find customer by national ID
    public Optional<Customer> findByNationalId(String nationalId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "FROM Customer WHERE nationalId = :nationalId";
            Query<Customer> query = session.createQuery(hql, Customer.class);
            query.setParameter("nationalId", nationalId);
            Customer result = query.uniqueResult();
            transaction.commit();
            return Optional.ofNullable(result);
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

    // Get customer by ID
    public Customer getById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            transaction.commit();
            return customer;
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

    // List all customers
    public List<Customer> getAllCustomers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            List<Customer> customers = session.createQuery("FROM Customer", Customer.class).list();
            transaction.commit();
            return customers;
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
