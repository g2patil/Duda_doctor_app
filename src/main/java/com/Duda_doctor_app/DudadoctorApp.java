package com.Duda_doctor_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication//(exclude = {SecurityConf.class})
@EnableJpaRepositories(basePackages = { "model","service","repository"})
@EntityScan(basePackages = {"model","service","repository"})
@ComponentScan(basePackages = {"com.Duda_doctor_app", "model","service","repository"})
public class DudadoctorApp {

	public static void main(String[] args) {
		SpringApplication.run(DudadoctorApp.class, args);
	}

}
