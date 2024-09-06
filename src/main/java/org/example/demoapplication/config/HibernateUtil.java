package org.example.demoapplication.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
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
                hibernateProperties.put(Environment.HBM2DDL_AUTO, "create");

                configuration.setProperties(hibernateProperties);

                // Add annotated classes (Entities)
                configuration.addAnnotatedClass(org.example.demoapplication.entity.Customer.class);
                configuration.addAnnotatedClass(org.example.demoapplication.entity.Deposit.class);
                configuration.addAnnotatedClass(org.example.demoapplication.entity.DepositType.class);

                sessionFactory = configuration.buildSessionFactory();

            } catch (Exception e) {
                e.printStackTrace();
                if (sessionFactory != null) {
                    sessionFactory.close();
                }
                throw new RuntimeException("There was an error building the session factory.");
            }
        }
        return sessionFactory;
    }
}
