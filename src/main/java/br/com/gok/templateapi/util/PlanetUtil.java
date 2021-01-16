package br.com.gok.templateapi.util;

import br.com.gok.templateapi.dto.PlanetDTO;
import br.com.gok.templateapi.model.Planet;

public class PlanetUtil {

    public static Long parsePopulation(String strNum) {
        try {
            return Long.parseLong(strNum);
        } catch (NumberFormatException | NullPointerException nfe ) {
            return null;
        } 
    }
    
    public static void convert(Planet planet, PlanetDTO dto){
        planet.setClimate(dto.getClimate());
        planet.setName(dto.getName());
        planet.setTerrain(dto.getTerrain());
        planet.setFilms(Long.valueOf(dto.getFilms().size()));
        planet.setPopulation(parsePopulation(dto.getPopulation()));
    }
    
}
