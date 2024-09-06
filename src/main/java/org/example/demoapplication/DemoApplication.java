package org.example.demoapplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.example.demoapplication.config.HibernateUtil;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Banking API", version = "1.0"))
public class DemoApplication {

	private static SessionFactory sessionFactory;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostConstruct
	public void init() {
		// Initialize Hibernate SessionFactory
		sessionFactory = HibernateUtil.getSessionFactory();
		System.out.println("Hibernate SessionFactory initialized.");
	}

	@PreDestroy
	public void destroy() {
		// Close Hibernate SessionFactory
		if (sessionFactory != null && !sessionFactory.isClosed()) {
			sessionFactory.close();
			System.out.println("Hibernate SessionFactory closed.");
		}
	}
}
