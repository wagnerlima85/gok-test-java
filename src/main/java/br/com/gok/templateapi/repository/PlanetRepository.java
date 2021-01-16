package br.com.gok.templateapi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.gok.templateapi.model.Planet;

public interface PlanetRepository extends CrudRepository<Planet,Long>, PlanetRepositoryCustom {
    Optional<Planet> findByName(String name);
}
