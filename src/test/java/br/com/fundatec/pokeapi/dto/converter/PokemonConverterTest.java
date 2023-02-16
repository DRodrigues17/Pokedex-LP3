package br.com.fundatec.pokeapi.dto.converter;

import br.com.fundatec.pokeapi.dto.MoveDTO;
import br.com.fundatec.pokeapi.dto.MovesDTO;
import br.com.fundatec.pokeapi.dto.PokemonDTO;
import br.com.fundatec.pokeapi.model.Pokemon;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PokemonConverterTest {

    @Test
    void shouldConvertToDTOWithSuccess() {
        Pokemon pokemon = Pokemon.builder()
                .externalId(20)
                .name(StringUtils.capitalize("charmander"))
                .height(7)
                .weight(30)
                .moves(List.of(new MovesDTO(new MoveDTO("throwFire"))))
                .build();
        assertInstanceOf(PokemonDTO.class, PokemonConverter.convertToDTO(pokemon));
    }

    @Test
    void shouldConvertToEntityWothSuccess() {
        PokemonDTO pokemon = new PokemonDTO(
                20,
                StringUtils.capitalize("charmander"),
                7,
                30,
                List.of(new MovesDTO(new MoveDTO("throwFire"))));
        assertInstanceOf(Pokemon.class, PokemonConverter.convertToEntity(pokemon));
    }
}