package org.example.demoapplication.dao;

import org.example.demoapplication.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void saveCustomer(Customer customer) {
        sessionFactory.getCurrentSession().saveOrUpdate(customer); // save or update customer
    }


    // Find customer by customerNumber
    public Optional<Customer> findByCustomerNumber(String customerNumber) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Customer WHERE customerNumber = :customerNumber", Customer.class)
                .setParameter("customerNumber", customerNumber)
                .uniqueResultOptional();
    }

    // Find customer by national ID
    public Optional<Customer> findByNationalId(String nationalId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Customer WHERE nationalId = :nationalId";
        return session.createQuery(hql, Customer.class)
                .setParameter("nationalId", nationalId)
                .uniqueResultOptional();
    }

    // Get customer by ID
    public Customer getById(Long id) {
        return sessionFactory.getCurrentSession().get(Customer.class, id);
    }

    // List all customers
    public List<Customer> getAllCustomers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Customer", Customer.class).list();
    }

}

