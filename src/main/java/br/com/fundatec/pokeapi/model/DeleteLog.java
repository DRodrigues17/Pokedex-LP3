package br.com.fundatec.pokeapi.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Data
@Document
public class DeleteLog {

    @Id
    private String id;
    private String pokemonName;
    private int pokemonId;
    private LocalDateTime dateOfRemove;


}
