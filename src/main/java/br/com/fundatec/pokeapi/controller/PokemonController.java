package br.com.fundatec.pokeapi.controller;

import br.com.fundatec.pokeapi.model.BaseResponse;
import br.com.fundatec.pokeapi.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

import static org.springframework.http.HttpStatus.ACCEPTED;

@RestController
@RequestMapping("/pokemons")
@RequiredArgsConstructor
public class PokemonController {

    private final PokemonService service;

    @GetMapping("/id/{id}")
    public ResponseEntity<BaseResponse> findById(@PathVariable("id") int id) {
        return ResponseEntity.ok(
                BaseResponse.builder().timestamp(LocalDateTime.now())
                        .data(Map.of("id",
                                service.findById(id)))
                        .message("pokemon presente na API com o id " + id)
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value()).build()
        );
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<BaseResponse> findByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(
                BaseResponse.builder().timestamp(LocalDateTime.now())
                        .data(Map.of("name",
                                service.findByName(name)))
                        .message("pokemon presente na API com o nome " + name)
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value()).build()
        );
    }

    @GetMapping("/list/weight/{quilograma}")
    public ResponseEntity<BaseResponse> findByWeight(@PathVariable("quilograma") int quilograma){
        return ResponseEntity.ok(
                BaseResponse.builder().timestamp(LocalDateTime.now())
                        .data(Map.of("pokemons", service
                                .findByWeight(quilograma)))
                        .message("pokemons presentes na API o peso de " + quilograma + "KG")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value()).build()
        );
    }

    @GetMapping("/list/height/{metros}")
    public ResponseEntity<BaseResponse> findByHeigth(@PathVariable("metros") int metros){
        return ResponseEntity.ok(
                BaseResponse.builder().timestamp(LocalDateTime.now())
                        .data(Map.of("pokemons", service
                                .findByHeight(metros)))
                        .message("pokemons presentes na API com a altura de " + metros + " metros")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value()).build()
        );
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse> deleteById(@PathVariable("id") int id) {
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("deletado", service.deleteById(id)))
                        .message("Este pokemon foi deletado")
                        .status(ACCEPTED).statusCode(ACCEPTED.value()).build()
        );
    }

}
