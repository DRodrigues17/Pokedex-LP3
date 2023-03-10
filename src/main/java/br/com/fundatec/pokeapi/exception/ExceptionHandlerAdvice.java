package br.com.fundatec.pokeapi.exception;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(PokemonNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorDTO> handleNotFound(PokemonNotFoundException e) {
        logger.error(e.getMessage());
        return new ResponseEntity<>(buildError("pokemon não encontrado"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public ResponseEntity<ApiErrorDTO> handleUnkownError(Throwable e) {
        logger.error(e.getMessage());
        return new ResponseEntity<>(buildError("Este erro é desconhecido, então não temos um tratamento para isso ainda"),
                HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(PokemonAlreadyDeletedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ApiErrorDTO> handleAlreadyDeleted(PokemonAlreadyDeletedException e) {
        logger.error(e.getMessage());
        return new ResponseEntity<>(buildError("Este pokemon já foi deletado, logo não é possivel deletá-lo"), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ApiErrorDTO> handleEmptyList(Throwable e) {
        logger.error(e.getMessage());
        return new ResponseEntity<>(buildError("Nenhum pokemon se encaixa nesses parametros"), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorDTO> handleConstraintError(MethodArgumentTypeMismatchException e) {
        logger.error(e.getMessage());
        return new ResponseEntity<>(buildError("Este método espera um parametro de tipo diferente"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ApiErrorDTO> feignClientExceptionHandler(FeignException exception) {
        logger.error(exception.getMessage());

        return switch (exception.status()) {
            case 404 -> {
                String searchKey = getSearchKey(exception.request().url());
                yield ResponseEntity.status(HttpStatus.valueOf(exception.status())).body(buildError("Não foi possivel encontrar pokemon " + searchKey));
            }
            case 503 ->
                    ResponseEntity.status(HttpStatus.valueOf(exception.status())).body(buildError("A API externa está indisponível"));
            case 502 -> ResponseEntity.status(HttpStatus.valueOf(exception.status())).body(buildError("Bad Gateway"));
            default ->
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildError("Há algum erro que não conseguimos identificar"));
        };
    }

    private ApiErrorDTO buildError(String message) {
        return new ApiErrorDTO(message, LocalDateTime.now());
    }

    private String getSearchKey(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }
}

