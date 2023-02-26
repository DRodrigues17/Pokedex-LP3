package br.com.fundatec.pokeapi.stubs;

import br.com.fundatec.pokeapi.dto.MoveDTO;
import br.com.fundatec.pokeapi.dto.MovesDTO;
import br.com.fundatec.pokeapi.dto.PokemonDTO;
import br.com.fundatec.pokeapi.model.Pokemon;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class PokemonStub {
    public static Pokemon createPokemonStub() {
        return Pokemon.builder()
                .externalId(17)
                .name(StringUtils.capitalize("pigeotto"))
                .height(11)
                .weight(300)
                .moves(List.of(new MovesDTO(new MoveDTO("razor-wind"))))
                .build();
    }

    public static Pokemon createDeletedPokemonStub() {
        return Pokemon.builder()
                .externalId(17)
                .name(StringUtils.capitalize("pigeotto"))
                .height(11)
                .weight(300)
                .moves(List.of(new MovesDTO(new MoveDTO("razor-wind"))))
                .deleted(true)
                .deletedAt(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")))
                .build();
    }

    public static PokemonDTO createPokemonDTOStub() {
        return new PokemonDTO(
                17,
                StringUtils.capitalize("pigeotto"),
                11,
                300,
                List.of(new MovesDTO(new MoveDTO("razor-wind"))));
    }


}
