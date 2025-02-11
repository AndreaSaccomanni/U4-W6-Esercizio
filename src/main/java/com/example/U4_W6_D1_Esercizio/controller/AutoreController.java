package com.example.U4_W6_D1_Esercizio.controller;

import com.example.U4_W6_D1_Esercizio.model.Autore;
import com.example.U4_W6_D1_Esercizio.model.BlogPost;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autori")
public class AutoreController {

    private List<Autore> allAutori = new ArrayList<>();

    // Vedere tutti gli autori
    //POSTMAN -> http:localhost:8080/autori/all
    @GetMapping("/all")
    public List<Autore> getAllAutori(){
        return allAutori;
    }

    // Vedere un singolo autore
    //POSTMAN -> http:localhost:8080/autori/getById/{id}
    @GetMapping("/getById/{id}")
    public Autore  getSingleBlogPost(@PathVariable int id){
        return allAutori.stream()
                .filter(autore -> autore.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Autore con id " + id + " non trovato"));
    }


    // Aggiungere un nuovo autore
    //POSTMAN -> http:localhost:8080/autori/create
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Autore createAutore(@RequestBody Autore newAutore) {
        Autore autore = new Autore(
                newAutore.getNome(),
                newAutore.getCognome(),
                newAutore.getEmail(),
                newAutore.getDataDiNascita()
        );
        allAutori.add(autore);
        return autore;
    }

    // Modificare uno specifico autore tramite id
    //POSTMAN -> http:localhost:8080/autori/update/{id}
    @PutMapping("/update/{id}")
    public Autore updateAutore(@PathVariable int id, @RequestBody Autore updatedAutore) {
        Optional<Autore> existingAutore = allAutori.stream()
                .filter(autore -> autore.getId() == id)
                .findFirst();

        if (existingAutore.isPresent()) {
            Autore autore = existingAutore.get();
            autore.setNome(updatedAutore.getNome());
            autore.setCognome(updatedAutore.getCognome());
            autore.setEmail(updatedAutore.getEmail());
            autore.setDataDiNascita(updatedAutore.getDataDiNascita());
            autore.setAvatar(updatedAutore.getAvatar());
            return autore;
        }
        return null;
    }

    // Cancellare uno specifico autore post tramite id
    //POSTMAN -> http:localhost:8080/autori/delete/{id}
    @DeleteMapping("/delete/{id}")
    public String deleteBlogPost(@PathVariable int id) {
        boolean removed = allAutori.removeIf(autore -> autore.getId() == id);
        return removed ? "Autore eliminato con successo." : "Autore non trovato.";
    }




}
