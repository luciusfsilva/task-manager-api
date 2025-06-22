package com.manager.task_manager_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("Task Manager API").version("1.0.0")
				.description("API for managing tasks in a task manager application")
				.termsOfService("https://example.com/terms").contact(new Contact().name("Support Team")
						.url("https://example.com/support").email("task-api-mail@test.com")));
	}
}