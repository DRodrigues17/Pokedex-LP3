package br.com.fundatec.pokeApi.controller;

import br.com.fundatec.pokeApi.dto.PokemonResponseDTO;
import br.com.fundatec.pokeApi.dto.converter.PokemonConverter;
import br.com.fundatec.pokeApi.model.Pokemon;
import br.com.fundatec.pokeApi.model.Response;
import br.com.fundatec.pokeApi.service.PokemonIntegrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/pokemons")
@CrossOrigin(origins = "*")
public class PokemonController {

    private PokemonIntegrationService service;
    private PokemonConverter<Pokemon, PokemonResponseDTO> converter;

    public PokemonController(PokemonIntegrationService pokemonIntegrationService) {
        this.service = pokemonIntegrationService;
    }

    @GetMapping("/list")
    public ResponseEntity<Response> findAll(){
        return ResponseEntity.ok(
                Response.builder().timestamp(LocalDateTime.now())
                        .data(Map.of("pokemons", service
                                .findAll()
                                .stream()
                                .map(converter::convert).toList()))
                        .message("pokemons presentes na API")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value()).build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> findById(@PathVariable("id") int id) {
        return ResponseEntity.ok(
                Response.builder().timestamp(LocalDateTime.now())
                        .data(Map.of("pokemon",
                                converter.convert(service.findById(id))))
                        .message("pokemons presentes na API")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value()).build()
        );
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> DeleteById(@PathVariable("id") int id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("deletado", service.deleteById(id)))
                        .message("Este pokemon foi deletado")
                        .status(NO_CONTENT).statusCode(NO_CONTENT.value()).build()
        );
    }

}
