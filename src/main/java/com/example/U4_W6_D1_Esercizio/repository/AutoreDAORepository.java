package com.example.U4_W6_D1_Esercizio.repository;

import com.example.U4_W6_D1_Esercizio.model.Autore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoreDAORepository extends JpaRepository<Autore, Long> {
}
