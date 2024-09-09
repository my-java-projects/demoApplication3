package org.example.demoapplication.config;

import org.example.demoapplication.dao.CustomerDAO;
import org.example.demoapplication.dao.DepositDAO;
import org.example.demoapplication.dao.DepositTypeDAO;
import org.example.demoapplication.entity.Customer;
import org.example.demoapplication.entity.Deposit;
import org.example.demoapplication.entity.DepositType;
import org.example.demoapplication.service.CustomerService;
import org.example.demoapplication.service.DepositService;
import org.example.demoapplication.service.DepositTypeService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;

import java.util.Properties;



@org.springframework.context.annotation.Configuration
public class AppConfig {

    @Bean
    public SessionFactory sessionFactory() {
        try {
            Configuration configuration = new Configuration();

            // Hibernate settings equivalent to hibernate.cfg.xml
            Properties hibernateProperties = new Properties();
            hibernateProperties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            hibernateProperties.put(Environment.URL, "jdbc:mysql://localhost:3306/bankdb");
            hibernateProperties.put(Environment.USER, "root");
            hibernateProperties.put(Environment.PASS, "adm!n");
            hibernateProperties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");

            // Optional Hibernate settings
            hibernateProperties.put(Environment.SHOW_SQL, "true");
            hibernateProperties.put(Environment.HBM2DDL_AUTO, "update");

            configuration.setProperties(hibernateProperties);

            // Add annotated classes (Entities)
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(Deposit.class);
            configuration.addAnnotatedClass(DepositType.class);

            return configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("There was an error building the session factory.");
        }
    }

    @Bean
    public DepositDAO depositDAO() {
        return new DepositDAO(sessionFactory());
    }

    @Bean
    public CustomerDAO customerDAO() {
        return new CustomerDAO(sessionFactory());
    }

    @Bean
    public DepositTypeDAO depositTypeDAO() {
        return new DepositTypeDAO(sessionFactory());
    }

    @Bean
    public CustomerService customerService(CustomerDAO customerDAO) {
        return new CustomerService(customerDAO);
    }

    @Bean
    public DepositTypeService depositTypeService(DepositTypeDAO depositTypeDAO) {
        return new DepositTypeService(depositTypeDAO);
    }

    @Bean
    public DepositService depositService(DepositDAO depositDAO, CustomerDAO customerDAO, DepositTypeDAO depositTypeDAO) {
        return new DepositService(depositDAO, customerDAO, depositTypeDAO);
    }
}
