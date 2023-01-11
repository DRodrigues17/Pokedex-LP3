package br.com.fundatec.pokeapi.exception;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String message) {
        super("não foi possivel encontrar "  + message);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
