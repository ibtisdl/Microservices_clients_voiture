package com.example.VoitureMicroservices.controllers;

import com.example.VoitureMicroservices.entities.Client;
import com.example.VoitureMicroservices.entities.Voiture;
import com.example.VoitureMicroservices.repositories.VoitureRepository;
import com.example.VoitureMicroservices.services.ClientService;
import com.example.VoitureMicroservices.services.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VoitureController {
    @Autowired
    VoitureRepository voitureRepository;
    @Autowired
    ClientService clientService;
    VoitureService voitureService;

    @GetMapping(value  =  "/voitures",  produces  =  {"application/json"})
    public ResponseEntity<Object> findAll() {
        try  {
            List<Voiture> voitures    =    voitureRepository.findAll();
            return  ResponseEntity.ok(voitures);
        } catch (Exception e) {
            return   ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error  fetching  voitures:  "  +  e.getMessage());
        }
    }

    @GetMapping("/voitures/{Id}")
    public  ResponseEntity<Object>  findById(@PathVariable Long  Id)  {
        try  {
            Voiture   voiture   =   voitureRepository.findById(Id)
                    .orElseThrow(()  ->  new  Exception("Voiture  Introuvable"));

            voiture.setClient(clientService.clientById(voiture.getClientId()));

            return  ResponseEntity.ok(voiture);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Voiture not  found with ID: "  + Id);
        }
    }

    @GetMapping("/voitures/client/{Id}")
    public ResponseEntity<List<Voiture>> findByClient(@PathVariable Long Id) {
        try  {
            Client client    =    clientService.clientById(Id);
            if (client != null) {
                List<Voiture>  voitures  =  voitureRepository.clientById(Id);
                return  ResponseEntity.ok(voitures);
            }  else  {
                return    ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/voitures/{clientId}")
    public ResponseEntity<Object> save(@PathVariable Long clientId, @RequestBody Voiture voiture) {
        try  {
            Client    client    =    clientService.clientById(clientId);

            if (client != null) {
                voiture.setClient(client);



                voiture.setClientId(clientId);
                voiture.setClient(client);
                Voiture  savedVoiture  =  voitureService.enregistrerVoiture(voiture);

                return ResponseEntity.ok(savedVoiture);
            }  else  {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)


                        .body("Client not found with ID: " + clientId);
            }




        } catch (Exception e) {




            return   ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)




                    .body("Error saving voiture: " + e.getMessage());


        }


    }






    @PutMapping("/voitures/{Id}")


    public ResponseEntity<Object> update(@PathVariable Long Id, @RequestBody
    Voiture updatedVoiture) { try {


        Voiture existingVoiture  =  voitureRepository.findById(Id)


                .orElseThrow(() -> new Exception("Voiture not found with ID:"+ Id));


        if (updatedVoiture.getMatricule() != null &&
                !updatedVoiture.getMatricule().isEmpty()) { existingVoiture.setMatricule(updatedVoiture.getMatricule());


        }

        if (updatedVoiture.getMarque() != null &&
                !updatedVoiture.getMarque().isEmpty()) { existingVoiture.setMarque(updatedVoiture.getMarque());


        }

        if (updatedVoiture.getModel() != null &&
                !updatedVoiture.getModel().isEmpty()) { existingVoiture.setModel(updatedVoiture.getModel());


        }

        Voiture savedVoiture  =  voitureRepository.save(existingVoiture);

        return ResponseEntity.ok(savedVoiture);

    } catch (Exception e) {

        return   ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating voiture: " + e.getMessage());




    }


    }




















}







