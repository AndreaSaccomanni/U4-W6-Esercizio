package com.example.U4_W6_D1_Esercizio.service;

import com.example.U4_W6_D1_Esercizio.model.Autore;
import com.example.U4_W6_D1_Esercizio.repository.AutoreDAORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutoreService {

    @Autowired
    private AutoreDAORepository autoreRepository;

    //metodo di inserimento di unn nuovo autore
    //il nuovo autoreverrà inserit lato client
    //ritornerà l'id del nuovo autore generato
    public Long nuovoAutore(Autore nuovoAutore){
        Autore autoreInserito = autoreRepository.save(nuovoAutore);
        return autoreInserito.getId();
    }

    //verrà gestito dal controller il caso in cui un autore viene recuperato
    //oppure l'id non corrisponde a nessun autore
    public Optional<Autore> ricercaAutoreById(Long id){
        return autoreRepository.findById(id);
    }

    public Optional<Autore> aggiornaAutore(Long id, Autore autoreAggiornato) {
        return autoreRepository.findById(id).map(autore -> {
            autore.setNome(autoreAggiornato.getNome());
            autore.setCognome(autoreAggiornato.getCognome());
            autore.setDataDiNascita(autoreAggiornato.getDataDiNascita());
            return autoreRepository.save(autore);
        });
    }

    // Metodo per eliminare un autore per ID
    public boolean eliminaAutore(Long id) {
        if (autoreRepository.existsById(id)) {
            autoreRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //metodo per avere tutti gli autori
    public List<Autore> getAllAutori() {
        return autoreRepository.findAll();
    }




}
