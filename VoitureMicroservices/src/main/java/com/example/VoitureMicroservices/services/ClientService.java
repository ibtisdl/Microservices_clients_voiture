package com.example.VoitureMicroservices.services;

import com.example.VoitureMicroservices.entities.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SERVICE-CLIENT")
public interface ClientService {
    @GetMapping(path = "/clients/{id}")
    public Client clientById(@PathVariable Long id);
}

