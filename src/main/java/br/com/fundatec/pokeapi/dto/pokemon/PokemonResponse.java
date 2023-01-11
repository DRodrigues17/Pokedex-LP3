package br.com.fundatec.pokeapi.dto.pokemon;

import br.com.fundatec.pokeapi.dto.MovesDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;


import java.util.Collection;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PokemonResponse {

    private Integer id;

    private String name;
    private int height;
    private int weight;
    private Collection<MovesDTO> moves;

}
