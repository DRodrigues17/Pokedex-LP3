package br.com.fundatec.pokeapi.dto.pokemon;

import br.com.fundatec.pokeapi.dto.MovesDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PokemonRequest {

    private Integer id;
    private String name;
    private int height;
    private int weight;
    private Collection<MovesDTO> moves;

}
