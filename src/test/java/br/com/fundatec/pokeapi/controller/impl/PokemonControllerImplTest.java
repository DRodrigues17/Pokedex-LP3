package br.com.fundatec.pokeapi.controller.impl;

import br.com.fundatec.pokeapi.exception.PokemonAlreadyDeletedException;
import br.com.fundatec.pokeapi.exception.PokemonNotFoundException;
import br.com.fundatec.pokeapi.service.PokemonService;
import br.com.fundatec.pokeapi.stubs.PokemonStub;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.containsString;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PokemonControllerImplTest {

    @MockBean
    PokemonService service;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = this.port;
    }

    @Test
    void shouldFindByIdSuccessfully() {
        Mockito.when(service.findById(17)).thenReturn(Optional.of(PokemonStub.createPokemonDTOStub()));
        get("/pokedex/api/v1/pokemons/id/{id}", 17)
                .then()
                .statusCode(200)
                .assertThat()
                .body(containsString("\"statusCode\":200,\"status\":\"OK\",\"message\":\"pokemon presente na API com o id 17\",\"data\":{\"id\":{\"id\":17,\"name\":\"Pigeotto\",\"height\":11,\"weight\":300,\"moves\":[{\"move\":{\"name\":\"razor-wind\""));

    }

    @Test
    void shouldThrowNotFoundWhenTryToFindByIdWithRandomNumber() {
        Mockito.when(service.findById(1123456)).thenThrow(PokemonNotFoundException.class);
        get("/pokedex/api/v1/pokemons/id/{id}", 1123456)
                .then()
                .statusCode(404)
                .assertThat()
                .body(containsString("{\"message\":\"pokemon não encontrado\""));
    }

    @Test
    void shouldFindByNameSuccessfully() {
        Mockito.when(service.findByName("pigeotto")).thenReturn(Optional.of(PokemonStub.createPokemonDTOStub()));
        get("/pokedex/api/v1/pokemons/name/{name}", "pigeotto")
                .then()
                .statusCode(200)
                .assertThat()
                .body(containsString("\"statusCode\":200,\"status\":\"OK\",\"message\":\"pokemon presente na API com o nome pigeotto\",\"data\":{\"name\":{\"id\":17,\"name\":\"Pigeotto\",\"height\":11,\"weight\":300,\"moves\":[{\"move\":{\"name\":\"razor-wind\""));

    }

    @Test
    void shouldThrowNotFoundWhenTryToFindByNameWithRandomWord() {
        Mockito.when(service.findByName("daniel")).thenThrow(PokemonNotFoundException.class);
        get("/pokedex/api/v1/pokemons/name/{name}", "daniel")
                .then()
                .statusCode(404)
                .assertThat()
                .body(containsString("{\"message\":\"pokemon não encontrado\""));
    }

    @Test
    void ShouldListByWeightSuccessfully() {
        Mockito.when(service.findByWeight(300)).thenReturn(List.of(PokemonStub.createPokemonDTOStub()));
        get("/pokedex/api/v1/pokemons/list/weight/{hectograms}", 300)
                .then()
                .statusCode(200)
                .assertThat()
                .body(containsString("\"statusCode\":200,\"status\":\"OK\",\"message\":\"pokemons presentes na API o peso de 300 hectogramas\",\"data\":{\"pokemons\":[{\"id\":17,\"name\":\"Pigeotto\""));
    }

    @Test
    void ShouldThrowIllegalStateWhenTryToListByWeightAndListIsEmpty() {
        Mockito.when(service.findByWeight(777)).thenThrow(IllegalStateException.class);
        get("/pokedex/api/v1/pokemons/list/weight/{hectograms}", 777)
                .then()
                .statusCode(406)
                .assertThat()
                .body(containsString("{\"message\":\"Nenhum pokemon se encaixa nesses parametros\""));
    }

    @Test
    void ShouldListByHeightSuccessfully() {
        Mockito.when(service.findByHeight(11)).thenReturn(List.of(PokemonStub.createPokemonDTOStub()));
        get("/pokedex/api/v1/pokemons/list/height/{decimeters}", 11)
                .then()
                .statusCode(200)
                .assertThat()
                .body(containsString("\"statusCode\":200,\"status\":\"OK\",\"message\":\"pokemons presentes na API com a altura de 11 decimetros\",\"data\":{\"pokemons\":[{\"id\":17,\"name\":\"Pigeotto\""));
    }

    @Test
    void ShouldThrowIllegalStateWhenTryToListByHeightAndListIsEmpty() {
        Mockito.when(service.findByHeight(777)).thenThrow(IllegalStateException.class);
        get("/pokedex/api/v1/pokemons/list/height/{decimeters}", 777)
                .then()
                .statusCode(406)
                .assertThat()
                .body(containsString("{\"message\":\"Nenhum pokemon se encaixa nesses parametros\""));
    }

    @Test
    void shouldDeleteByNameWithSuccess() {
        Mockito.when(service.deleteByName("daniel")).thenReturn(true);
        delete("/pokedex/api/v1/pokemons/delete/{name}", "daniel")
                .then()
                .statusCode(200)
                .assertThat()
                .body(containsString("\"statusCode\":202,\"status\":\"ACCEPTED\",\"message\":\"Este pokemon foi deletado\",\"data\":{\"deletado\":true}}"));

    }

    @Test
    void shouldThrowNotFoundWhenTryToDeleteInexistentPokemon() {
        Mockito.when(service.deleteByName("daniel")).thenThrow(PokemonNotFoundException.class);
        delete("/pokedex/api/v1/pokemons/delete/{name}", "daniel")
                .then()
                .statusCode(404)
                .assertThat()
                .body(containsString("{\"message\":\"pokemon não encontrado\""));

    }

    @Test
    void shouldThrowAlreadyDeletedWhenTryToDeleteAlreadyDeletedPokemon() {
        Mockito.when(service.deleteByName("daniel")).thenThrow(PokemonAlreadyDeletedException.class);
        delete("/pokedex/api/v1/pokemons/delete/{name}", "daniel")
                .then()
                .statusCode(405)
                .assertThat()
                .body(containsString("{\"message\":\"Este pokemon já foi deletado, logo não é possivel deletá-lo\""));

    }
}