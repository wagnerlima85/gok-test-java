package br.com.gok.templateapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.gok.templateapi.model.Planet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.gok.templateapi.client.PlanetClient;
import br.com.gok.templateapi.dto.PlanetDTO;
import br.com.gok.templateapi.dto.StarWarsPlanetsDTO;
import br.com.gok.templateapi.exception.InternalServerErrorException;
import br.com.gok.templateapi.repository.PlanetRepository;
import br.com.gok.templateapi.util.PlanetUtil;
import feign.FeignException;

@Service
public class PlanetService {

    private final PlanetClient client;
    private final PlanetRepository repository;

    @Autowired
    public PlanetService(PlanetClient client, PlanetRepository repository){
        this.client = client;
        this.repository = repository;
    }

    @Scheduled(fixedDelay = 3600000)
    public void loadRefreshPlanets() throws FeignException, Exception {

        long currentPage = 0L;
        boolean hasNext = true;
        List<PlanetDTO> planetDTOs = new ArrayList<PlanetDTO>();
        while (hasNext) {
            currentPage++;
            StarWarsPlanetsDTO planets = client.getStarWarsPlanetsDTO(currentPage);
            hasNext = planets.getNext() == null ? false : true;

            if (hasNext)
                planets.getResults().forEach((planet) -> {planetDTOs.add(planet);});
        }

        this.savePlanets(planetDTOs);
    }

    public Iterable<Planet> list(){
        return repository.findAll();
    }

    public Planet findById(Long id) throws Exception{
        try {
            return repository.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }   

    public List<Planet> filterByName(String text){
        return repository.filterByName(text);
    }

    public List<Planet> filterByPopulation(Long min, Long max) throws InternalServerErrorException{
        if(min > max)
            throw new InternalServerErrorException("O valor final precisa ser maior que o inicial");
        return repository.filterByPopulation(min,max);
    }

    public String listStarWars(Long page){
        return client.getPlanets(page);
    }

    public void deleteByid(Long id) throws Exception{
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void savePlanets(List<PlanetDTO> planetDTOs) throws Exception{
        planetDTOs.stream().forEach(dto -> {
            Optional<Planet> planet = repository.findByName(dto.getName());
            if (planet.isEmpty()) {
                Planet obj = new Planet();
                PlanetUtil.convert(obj, dto);
                repository.save(obj);
            }
        });
    
    }
}
