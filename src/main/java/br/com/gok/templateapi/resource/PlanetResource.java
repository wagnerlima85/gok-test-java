package br.com.gok.templateapi.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gok.templateapi.model.Planet;
import br.com.gok.templateapi.model.resource.PlanetMapper;
import br.com.gok.templateapi.service.PlanetService;
import ma.glasnost.orika.MapperFacade;

@RestController
@RequestMapping("/api/template/v1/planets")
public class PlanetResource {
 
    private final MapperFacade mapper;
    private final PlanetService service;

    public PlanetResource(PlanetService service, MapperFacade mapper){
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<?> list() {
        Iterable<Planet> list = service.list();
        return ResponseEntity.ok().body(mapper.mapAsList(list, PlanetMapper.class));
    }

    @GetMapping("/filter/population")
    public ResponseEntity<?> listByPopulation(@RequestParam Long min, @RequestParam Long max) {
        List<Planet> list = service.filterByPopulation(min, max);
        return ResponseEntity.ok().body(mapper.mapAsList(list, PlanetMapper.class));
    }

    @GetMapping("/filter/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        List<Planet> list = service.filterByName(name);
        return ResponseEntity.ok().body(mapper.mapAsList(list, PlanetMapper.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) throws Exception {
        Planet planet = service.findById(id);
        if(planet == null)
            return ResponseEntity.ok().body(mapper.map(planet, PlanetMapper.class));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) throws Exception {
        service.deleteByid(id);
    }

    @GetMapping("/star-wars")
    public ResponseEntity<?> listStarWars(@RequestParam Long page) {
        if(page > 0)
            return ResponseEntity.ok(service.listStarWars(page));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
