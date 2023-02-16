package br.com.fundatec.pokeapi.exception;

public class PokemonAlreadyDeletedException extends RuntimeException {
    public PokemonAlreadyDeletedException(String message) {
        super(message);
    }
}
