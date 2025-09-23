package br.com.fiap.bank_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private static final String PROJETO = "API Bancária - CRUD";
    private static final String EQUIPE  = "Maria Eduarda F. Rocha, Victor de Carvalho Alves";

    @GetMapping("/")
    public String home() {
        return PROJETO + " | Equipe: " + EQUIPE;
    }
}
