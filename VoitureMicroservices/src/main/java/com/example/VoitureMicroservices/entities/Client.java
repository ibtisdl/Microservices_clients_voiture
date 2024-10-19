package com.example.VoitureMicroservices.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Client {
        private Long id;
        private String nom;
        private String prenom;
        private Float age;
    }


