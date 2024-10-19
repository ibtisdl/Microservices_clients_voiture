package com.example.VoitureMicroservices;

import com.example.VoitureMicroservices.entities.Client;
import com.example.VoitureMicroservices.entities.Voiture;
import com.example.VoitureMicroservices.repositories.VoitureRepository;
import com.example.VoitureMicroservices.services.ClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class VoitureMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoitureMicroservicesApplication.class, args);
	}

	@Bean
	CommandLineRunner initialiserBaseH2(VoitureRepository voitureRepository, ClientService clientService) {
		return args -> {
			Client c1 = clientService.clientById(2L);
			Client c2 = clientService.clientById(1L);

			System.out.println("**************************");
			System.out.println("Id est :" + c2.getId());
			System.out.println("Nom est :" + c2.getNom());
			System.out.println("**************************");

			voitureRepository.save(new Voiture(1L, "Toyota", "A 25 333", "Corolla", 1L, c2));
			voitureRepository.save(new Voiture(2L, "Renault", "B 6 3456", "Megane", 1L, c2));
			voitureRepository.save(new Voiture(3L, "Peugeot", "A 55 4444", "301", 2L, c1));
		};
	}
}

