package com.example.U4_W6_D1_Esercizio.controller;

import com.example.U4_W6_D1_Esercizio.model.Autore;
import com.example.U4_W6_D1_Esercizio.model.BlogPost;
import com.example.U4_W6_D1_Esercizio.repository.AutoreDAORepository;
import com.example.U4_W6_D1_Esercizio.service.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autori")
public class AutoreController {


    @Autowired
    private AutoreService autoreService;

    // Aggiungere un nuovo autore
    //POSTMAN -> http:localhost:8080/autori/new
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public String createAutore(@RequestBody Autore newAutore) {
        Long idGenerato = autoreService.nuovoAutore(newAutore);
        return "L'autore con id" + idGenerato + " è stato inserito correttamente";
    }

    // Vedere tutti gli autori
    //POSTMAN -> http:localhost:8080/autori/all
    @GetMapping("/all")
    public ResponseEntity<List<Autore>> getAllAutori() {
        List<Autore> autori = autoreService.getAllAutori();
        if (autori.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(autori, HttpStatus.OK);
        }
    }

    // Ottenere un singolo autore tramite id
    //POSTMAN -> http:localhost:8080/autori/getById/{idAutore}
    @GetMapping("/getById/{idAutore}")
    public ResponseEntity<Autore> getAutoreById(@PathVariable long idAutore) {
        Optional<Autore> autoreTrovato = autoreService.ricercaAutoreById(idAutore);
        if (autoreTrovato.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(autoreTrovato.get(), HttpStatus.OK);
            //devo usare .get() perchè autoreRicercato è un Optional
        }

    }

    // Aggiornare un autore per ID
    //POSTMAN -> http:localhost:8080/autori/update/{id}
    @PutMapping("/update/{id}")
    public ResponseEntity<Autore> updateAutore(@PathVariable Long id, @RequestBody Autore updatedAutore) {
        Optional<Autore> autoreAggiornato = autoreService.aggiornaAutore(id, updatedAutore);
        if (autoreAggiornato.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(autoreAggiornato.get(), HttpStatus.OK);
        }
    }

    // Eliminare un autore per ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAutore(@PathVariable Long id) {
        boolean eliminato = autoreService.eliminaAutore(id);
        if (eliminato) {
            return new ResponseEntity<>("Autore eliminato con successo.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Autore non trovato.", HttpStatus.NOT_FOUND);
        }
    }


}
