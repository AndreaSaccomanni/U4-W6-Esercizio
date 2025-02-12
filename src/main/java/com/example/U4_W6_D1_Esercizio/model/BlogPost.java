package com.example.U4_W6_D1_Esercizio.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name = "blog_posts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BlogPost {
    @Id
    @GeneratedValue
    private Long id;
    private String categoria;
    private String titolo;
//    private Cover cover = null;
    private String contenuto;
    private int tempoDiLettura;

    @ManyToOne
    @JoinColumn(name = "autore_id")
    private Autore autore;

    public BlogPost(String categoria, String titolo, String contenuto, int tempoDiLettura) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "categoria='" + categoria + '\'' +
                ", titolo='" + titolo + '\'' +
                ", contenuto='" + contenuto + '\'' +
                ", tempoDiLettura=" + tempoDiLettura +
                '}';
    }
}
