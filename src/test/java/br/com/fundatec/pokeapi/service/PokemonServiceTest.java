package br.com.fundatec.pokeapi.service;

import br.com.fundatec.pokeapi.client.PokemonClient;
import br.com.fundatec.pokeapi.dto.PokemonDTO;
import br.com.fundatec.pokeapi.exception.PokemonAlreadyDeletedException;
import br.com.fundatec.pokeapi.exception.PokemonNotFoundException;
import br.com.fundatec.pokeapi.model.DeleteLog;
import br.com.fundatec.pokeapi.model.Pokemon;
import br.com.fundatec.pokeapi.repository.DeleteLogRepository;
import br.com.fundatec.pokeapi.repository.PokemonRepository;
import br.com.fundatec.pokeapi.stubs.PokemonStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PokemonServiceTest {

    @Mock
    private PokemonRepository pokemonRepositoryMock;
    @Mock
    private PokemonClient pokemonClientMock;
    @Mock
    private DeleteLogRepository deleteLogRepositoryMock;

    @InjectMocks
    private PokemonService pokemonService;

    @Test
    void shouldFindPokemonByIdThatExistsOnRepository() {
        when(pokemonRepositoryMock.findByExternalIdAndDeletedFalse(17)).thenReturn(Optional.of(PokemonStub.createPokemonStub()));

        Optional<PokemonDTO> pokemonDTO = pokemonService.findById(17);

        Assertions.assertTrue(pokemonDTO.isPresent());
        assertEquals(PokemonStub.createPokemonDTOStub(), pokemonDTO.get());
        assertEquals("Pigeotto", pokemonDTO.get().name());
        verify(pokemonClientMock, never()).getPokemonById(17);
        verify(pokemonRepositoryMock, times(1)).findByExternalIdAndDeletedFalse(17);
    }

    @Test
    void shouldFindPokemonByIdThatExistsDontRepository() {
        when(pokemonRepositoryMock.findByExternalIdAndDeletedFalse(17)).thenReturn(Optional.empty());
        when(pokemonRepositoryMock.save(any(Pokemon.class))).thenReturn(null);
        when(pokemonClientMock.getPokemonById(17)).thenReturn(Optional.of(PokemonStub.createPokemonDTOStub()));


        Optional<PokemonDTO> pokemonDTO = pokemonService.findById(17);


        Assertions.assertTrue(pokemonDTO.isPresent());
        assertEquals(PokemonStub.createPokemonDTOStub(), pokemonDTO.get());
        assertEquals("Pigeotto", pokemonDTO.get().name());
        verify(pokemonClientMock, times(1)).getPokemonById(17);
        verify(pokemonRepositoryMock, times(1)).findByExternalIdAndDeletedFalse(17);
    }

    @Test
    void shouldThrowNotFoundWhenTryToFindByIdWithRandomNumber() {
        when(pokemonRepositoryMock.findByExternalIdAndDeletedFalse(17171717)).thenReturn(Optional.empty());
        when(pokemonClientMock.getPokemonById(17171717)).thenThrow(PokemonNotFoundException.class);

        assertThrows(PokemonNotFoundException.class, () -> pokemonService.findById(17171717));
        verify(pokemonClientMock, times(1)).getPokemonById(17171717);
        verify(pokemonRepositoryMock, times(1)).findByExternalIdAndDeletedFalse(17171717);
    }

    @Test
    void shouldFindPokemonByNameThatExistsOnRepository() {
        when(pokemonRepositoryMock.findByNameAndDeletedFalse("Pigeotto")).thenReturn(Optional.of(PokemonStub.createPokemonStub()));

        Optional<PokemonDTO> pokemonDTO = pokemonService.findByName("Pigeotto");

        Assertions.assertTrue(pokemonDTO.isPresent());
        assertEquals(PokemonStub.createPokemonDTOStub(), pokemonDTO.get());
        assertEquals(300, pokemonDTO.get().weight());
        verify(pokemonClientMock, never()).getPokemonByName("Pigeotto");
        verify(pokemonRepositoryMock, times(1)).findByNameAndDeletedFalse("Pigeotto");
    }

    @Test
    void shouldFindPokemonByNameThatExistsDontRepository() {
        when(pokemonRepositoryMock.findByNameAndDeletedFalse("Pigeotto")).thenReturn(Optional.empty());
        when(pokemonRepositoryMock.save(any(Pokemon.class))).thenReturn(null);
        when(pokemonClientMock.getPokemonByName("pigeotto")).thenReturn(Optional.of(PokemonStub.createPokemonDTOStub()));

        Optional<PokemonDTO> pokemonDTO = pokemonService.findByName("Pigeotto");

        Assertions.assertTrue(pokemonDTO.isPresent());
        assertEquals(PokemonStub.createPokemonDTOStub(), pokemonDTO.get());
        assertEquals(300, pokemonDTO.get().weight());
        verify(pokemonClientMock, times(1)).getPokemonByName("pigeotto");
        verify(pokemonRepositoryMock, times(1)).findByNameAndDeletedFalse("Pigeotto");
    }

    @Test
    void shouldThrowNotFoundWhenTryToFindByNameWithRandomWord() {
        when(pokemonRepositoryMock.findByNameAndDeletedFalse("Daniel")).thenReturn(Optional.empty());
        when(pokemonClientMock.getPokemonByName("daniel")).thenThrow(PokemonNotFoundException.class);

        assertThrows(PokemonNotFoundException.class, () -> pokemonService.findByName("Daniel"));
        verify(pokemonClientMock, times(1)).getPokemonByName("daniel");
        verify(pokemonRepositoryMock, times(1)).findByNameAndDeletedFalse("Daniel");
    }

    @Test
    void shouldListByWeightWithSuccess() {
        when(pokemonRepositoryMock.findByWeightAndDeletedFalse(300)).thenReturn(List.of(PokemonStub.createPokemonStub()));

        List<PokemonDTO> list = pokemonService.findByWeight(300);

        assertEquals(1, list.size());
        assertEquals(PokemonStub.createPokemonDTOStub().name(), list.get(0).name());
        verify(pokemonRepositoryMock, times(1)).findByWeightAndDeletedFalse(300);
    }

    @Test
    void ShouldThrowIllegalStateWhenTryToListByWeightAndListIsEmpty() {
        when(pokemonRepositoryMock.findByWeightAndDeletedFalse(999)).thenThrow(IllegalStateException.class);

        assertThrows(IllegalStateException.class, () -> pokemonService.findByWeight(999));
        verify(pokemonRepositoryMock, times(1)).findByWeightAndDeletedFalse(999);
    }

    @Test
    void shouldListByHeightWithSuccess() {
        when(pokemonRepositoryMock.findByHeightAndDeletedFalse(11)).thenReturn(List.of(PokemonStub.createPokemonStub()));

        List<PokemonDTO> list = pokemonService.findByHeight(11);

        assertEquals(1, list.size());
        assertEquals(PokemonStub.createPokemonDTOStub().moves(), list.get(0).moves());
        verify(pokemonRepositoryMock, times(1)).findByHeightAndDeletedFalse(11);
    }

    @Test
    void ShouldThrowIllegalStateWhenTryToListByHeightAndListIsEmpty() {
        when(pokemonRepositoryMock.findByHeightAndDeletedFalse(777)).thenThrow(IllegalStateException.class);

        assertThrows(IllegalStateException.class, () -> pokemonService.findByHeight(777));
        verify(pokemonRepositoryMock, times(1)).findByHeightAndDeletedFalse(777);
    }

    @Test
    void shouldDeleteByNameWithSuccess() {
        when(pokemonRepositoryMock.findByName("Pigeotto")).thenReturn(Optional.of(PokemonStub.createPokemonStub()));

        Optional<Pokemon> pokemon = pokemonRepositoryMock.findByName("Pigeotto");

        assertTrue(pokemonService.deleteByName("Pigeotto"));
        assertTrue(pokemon.get().isDeleted());
        assertNotNull(pokemon.get().getDeletedAt());
        verify(pokemonRepositoryMock, times(1)).save(pokemon.get());
        verify(deleteLogRepositoryMock, times(1)).save(ArgumentMatchers.any(DeleteLog.class));
    }

    @Test
    void shouldThrowNotFoundWhenTryToDeleteInexistentPokemon() {
        when(pokemonRepositoryMock.findByName("Daniel")).thenThrow(PokemonNotFoundException.class);

        assertThrows(PokemonNotFoundException.class, () -> pokemonService.deleteByName("Daniel"));
        verify(pokemonRepositoryMock, times(1)).findByName("Daniel");

    }

    @Test
    void shouldThrowAlreadyDeletedWhenTryToDeleteAlreadyDeletedPokemon() {
        when(pokemonRepositoryMock.findByName("Daniel")).thenReturn(Optional.of(PokemonStub.createDeletedPokemonStub()));

        assertThrows(PokemonAlreadyDeletedException.class, () -> pokemonService.deleteByName("Daniel"));
        verify(pokemonRepositoryMock, times(1)).findByName("Daniel");
    }
}