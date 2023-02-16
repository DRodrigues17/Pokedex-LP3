package br.com.fundatec.pokeapi.controller.impl;

import br.com.fundatec.pokeapi.controller.IPokemonController;
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
public class PokemonControllerImpl implements IPokemonController {

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

    @GetMapping("/list/weight/{hectograms}")
    public ResponseEntity<BaseResponse> findByWeight(@PathVariable("hectograms") int hectograms) {
        return ResponseEntity.ok(
                BaseResponse.builder().timestamp(LocalDateTime.now())
                        .data(Map.of("pokemons", service
                                .findByWeight(hectograms)))
                        .message("pokemons presentes na API o peso de " + hectograms + " hectogramas")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value()).build()
        );
    }

    @GetMapping("/list/height/{decimeters}")
    public ResponseEntity<BaseResponse> findByHeight(@PathVariable("decimeters") int decimeters) {
        return ResponseEntity.ok(
                BaseResponse.builder().timestamp(LocalDateTime.now())
                        .data(Map.of("pokemons", service
                                .findByHeight(decimeters)))
                        .message("pokemons presentes na API com a altura de " + decimeters + " decimetros")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value()).build()
        );
    }


    @DeleteMapping("/delete/{name}")
    public ResponseEntity<BaseResponse> deleteByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("deletado", service.deleteByName(name)))
                        .message("Este pokemon foi deletado")
                        .status(ACCEPTED).statusCode(ACCEPTED.value()).build()
        );
    }

}
