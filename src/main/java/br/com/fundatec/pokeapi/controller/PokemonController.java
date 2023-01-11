package br.com.fundatec.pokeapi.controller;

import br.com.fundatec.pokeapi.dto.pokemon.PokemonRequest;
import br.com.fundatec.pokeapi.dto.pokemon.PokemonResponse;
import br.com.fundatec.pokeapi.dto.pokemon.converter.PokemonConverter;
import br.com.fundatec.pokeapi.model.Pokemon;
import br.com.fundatec.pokeapi.model.Response;
import br.com.fundatec.pokeapi.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/pokemons")
@RequiredArgsConstructor
public class PokemonController {


    private final PokemonService service;
    private final PokemonConverter<Pokemon, PokemonResponse, PokemonRequest> converter;


    @GetMapping("/id/{id}")
    public ResponseEntity<Response> findById(@PathVariable("id") int id) {
        return ResponseEntity.ok(
                Response.builder().timestamp(LocalDateTime.now())
                        .data(Map.of("id",
                                converter.convert(service.findById(id))))
                        .message("pokemons presentes na API com o id " + id)
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value()).build()
        );
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Response> findByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(
                Response.builder().timestamp(LocalDateTime.now())
                        .data(Map.of("name",
                                converter.convert(service.findByName(name))))
                        .message("pokemon presente na API com o nome " + name)
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value()).build()
        );
    }

    @GetMapping("/list/peso/{hectograma}")
    public ResponseEntity<Response> findByWeight(@PathVariable("hectograma") int hectograma){
        return ResponseEntity.ok(
                Response.builder().timestamp(LocalDateTime.now())
                        .data(Map.of("pokemons", service
                                .findByWigth(hectograma)
                                .stream()
                                .map(converter::convert).toList()))
                        .message("pokemons presentes na API o peso de " + hectograma+ " em hectogramas")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value()).build()
        );
    }

    @GetMapping("/list/altura/{decimetros}")
    public ResponseEntity<Response> findByHeigth(@PathVariable("decimetros") int decimetros){
        return ResponseEntity.ok(
                Response.builder().timestamp(LocalDateTime.now())
                        .data(Map.of("pokemons", service
                                .findByHeigth(decimetros)
                                .stream()
                                .map(converter::convert).toList()))
                        .message("pokemons presentes na API com a altura de " + decimetros + " decimetros")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value()).build()
        );
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable("id") int id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("deletado", service.deleteById(id)))
                        .message("Este pokemon foi deletado")
                        .status(NO_CONTENT).statusCode(NO_CONTENT.value()).build()
        );
    }

}
