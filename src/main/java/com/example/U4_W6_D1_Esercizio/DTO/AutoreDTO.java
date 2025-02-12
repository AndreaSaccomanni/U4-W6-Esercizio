package com.example.U4_W6_D1_Esercizio.DTO;

import com.example.U4_W6_D1_Esercizio.model.Autore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoreDTO {
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataDiNascita;



    public static AutoreDTO fromEntityToDTO(Autore autore) {
        return new AutoreDTO(
                autore.getNome(),
                autore.getCognome(),
                autore.getEmail(),
                autore.getDataDiNascita()
        );
    }
}
