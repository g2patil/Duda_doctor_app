package com.Duda_doctor_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication//(exclude = {SecurityConf.class})
@EnableJpaRepositories(basePackages = {"dto" ,"model","service","repository", "security","exception"})
@EntityScan(basePackages = {"dto" ,"model","service","repository", "security","exception"})
@ComponentScan(basePackages = {"dto" ,"com.Duda_doctor_app", "model","service","repository", "security","exception"})
public class DudadoctorApp {

	public static void main(String[] args) {
		SpringApplication.run(DudadoctorApp.class, args);
	}

}
