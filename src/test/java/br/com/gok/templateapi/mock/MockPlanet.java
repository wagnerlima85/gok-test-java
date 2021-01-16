package br.com.gok.templateapi.mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import br.com.gok.templateapi.dto.PlanetDTO;
import br.com.gok.templateapi.dto.StarWarsPlanetsDTO;
import br.com.gok.templateapi.model.Planet;

public class MockPlanet {
    public static final Long PAGE = 1L;
    public static final Long ID = 1L;
    public static final String NAME = "Niburu";
    public static final String CLIMATE = "hot";
    public static final String TERRAIN = "solid";
    public static final Long POPULATION = 300000L;

    public static Planet completePlanet() {
        return Planet.builder()
                .id(ID)
                .name(NAME)
                .climate(CLIMATE)
                .terrain(TERRAIN)
                .population(POPULATION)
                .films(3L)
                .build();
    }

    public static Optional<Planet> optionalPlanet(){
        return Optional.of(completePlanet());
    }

    public static Planet incompletePlanet() {
        return Planet.builder()
                .id(ID)
                .name(NAME)
                .climate(CLIMATE)
                .population(POPULATION)
                .build();
    }
    
    public static PlanetDTO completePlanetDTO() {
        return PlanetDTO.builder()
                .id(ID)
                .name(NAME)
                .climate(CLIMATE)
                .terrain(TERRAIN)
                .population("300000")
                .films(Arrays.asList("Filme 1", "Filme 2", "Filme 3"))
                .build();
    }

    public static PlanetDTO incompletePlanetDTO() {
        return PlanetDTO.builder()
                .id(ID)
                .name(NAME)
                .climate(CLIMATE)
                .terrain(TERRAIN)
                .population("unknown")
                .films(Arrays.asList("Filme 1", "Filme 2", "Filme 3"))
                .build();
    }
    

    public static StarWarsPlanetsDTO getStarWarsPlanetsDTO(){
        return StarWarsPlanetsDTO.builder()
                .next("url")
                .results(Collections.singletonList(completePlanetDTO()))
                .build();
    }

    public static List<Planet> arrayCompletePlanet(){
        return Collections.singletonList(completePlanet());
    }

    public static List<PlanetDTO> arrayCompletePlanetDTO(){
        return Collections.singletonList(completePlanetDTO());
    }
    
}
