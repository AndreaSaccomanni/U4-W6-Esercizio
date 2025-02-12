package com.example.U4_W6_D1_Esercizio.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Entity
@Table(name = "autori")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Autore {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String nome;
    private String cognome;
    @Column(nullable = false)
    private String email;
    private LocalDate dataDiNascita;
    private String avatar = null;

    @OneToMany(mappedBy = "autore", cascade = CascadeType.ALL)
    private List<BlogPost> allPosts;

    public Autore(String nome, String cognome, String email, LocalDate dataDiNascita) {

        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataDiNascita = dataDiNascita;
    }


    @Override
    public String toString() {
        return "Autore{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", dataDiNascita=" + dataDiNascita +
                '}';
    }
}
