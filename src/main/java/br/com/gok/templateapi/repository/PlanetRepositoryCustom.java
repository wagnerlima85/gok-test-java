package br.com.gok.templateapi.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.gok.templateapi.model.Planet;


@Repository
public interface PlanetRepositoryCustom {
    List<Planet> filterByName(String text);
    List<Planet> filterByPopulation(Long min, Long max);
}
