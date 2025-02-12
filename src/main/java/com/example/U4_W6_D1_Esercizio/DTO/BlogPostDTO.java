package com.example.U4_W6_D1_Esercizio.DTO;

import com.example.U4_W6_D1_Esercizio.model.BlogPost;
//import com.example.U4_W6_D1_Esercizio.model.Cover;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostDTO {
    private String categoria;
    @Column(nullable = false)
    private String titolo;
    @Column(nullable = false)
    private String contenuto;
    private int tempoDiLettura;
    private String cover;


}
