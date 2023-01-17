package br.com.fundatec.pokeapi.dto.pokemon;

import br.com.fundatec.pokeapi.dto.MovesDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Collection;

@Builder
@Data
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PokemonRequest {

    private Integer id;
    private String name;
    private int height;
    private int weight;
    private Collection<MovesDTO> moves;

}
