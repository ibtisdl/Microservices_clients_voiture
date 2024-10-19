package com.example.ClientMicroservices;

import com.example.ClientMicroservices.entities.Client;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import com.example.ClientMicroservices.repositories.ClientRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class ClientMicroservicesApplication {

	public static void main(String[] args) {

		SpringApplication.run(ClientMicroservicesApplication.class, args);}
	
		@Bean
		CommandLineRunner initialiserBaseH2(ClientRepository clientRepository) {
			return args -> {
				clientRepository.save(new Client(1L, "Rabab SELIMANI", "Rabab", 23f));
				clientRepository.save(new Client(2L, "Amal RAMI", "Amal", 22f));
				clientRepository.save(new Client(3L, "Samir SAFI", "Samir", 22f));
			};


	}

}
