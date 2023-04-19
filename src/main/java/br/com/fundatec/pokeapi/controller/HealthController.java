package br.com.fundatec.pokeapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    @Operation(
            method = "GET",
            summary = "garantia de situação",
            description = "Retorna 200, confirmando que a API está de pé")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Poke-API de Daniel Funcionando!!!");
    }

}
