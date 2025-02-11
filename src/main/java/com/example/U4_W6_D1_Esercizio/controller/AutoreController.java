package com.example.U4_W6_D1_Esercizio.controller;

import com.example.U4_W6_D1_Esercizio.model.Autore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/autori")
public class AutoreController {

    private List<Autore> autori;

}
