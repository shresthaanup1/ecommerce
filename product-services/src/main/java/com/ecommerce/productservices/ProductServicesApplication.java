package com.ecommerce.productservices;

import com.ecommerce.productservices.utilities.DataInserter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("com.ecommerce.productservices.feignclients")
public class ProductServicesApplication implements CommandLineRunner {

	@Autowired
	DataInserter dataInserter;
	public static void main(String[] args) {
		SpringApplication.run(ProductServicesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		boolean insertCategories = false;
		boolean insertProudcts = false;

		if (insertCategories) {
			dataInserter.insertCategories();
		}

		if (insertProudcts) {
			dataInserter.insertProducts();
		}
	}
}
