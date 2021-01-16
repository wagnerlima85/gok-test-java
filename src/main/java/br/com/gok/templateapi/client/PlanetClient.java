package br.com.gok.templateapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.gok.templateapi.dto.StarWarsPlanetsDTO;

@FeignClient(name = "star-wars",url = "${star-wars.base-url}")
public interface PlanetClient {

	@GetMapping(value = "planets/")
	public StarWarsPlanetsDTO getStarWarsPlanetsDTO(@RequestParam Long page);

	@GetMapping(value = "planets/")
	public String getPlanets(@RequestParam Long page);
	
}
