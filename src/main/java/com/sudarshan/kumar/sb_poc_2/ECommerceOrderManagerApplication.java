package com.sudarshan.kumar.sb_poc_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ECommerceOrderManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceOrderManagerApplication.class, args);
	}

}
