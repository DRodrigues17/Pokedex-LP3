package br.com.fundatec.pokeApi.model;

import br.com.fundatec.pokeApi.dto.MovesDTO;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Data
@Document
public class Pokemon {

    @Id
    private Integer id;
    private String name;
    private int height;
    private int weight;
    private Collection<MovesDTO> moves;
}
