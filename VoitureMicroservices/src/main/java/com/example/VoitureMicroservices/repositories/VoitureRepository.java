package com.example.VoitureMicroservices.repositories;

import com.example.VoitureMicroservices.entities.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoitureRepository extends JpaRepository<Voiture, Long> {
}

