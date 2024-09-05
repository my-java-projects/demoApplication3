package org.example.demoapplication2;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Banking API", version = "1.0"))
public class DemoApplication2Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication2Application.class, args);
	}

}
