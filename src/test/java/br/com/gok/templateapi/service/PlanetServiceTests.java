package br.com.gok.templateapi.service;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.gok.templateapi.client.PlanetClient;
import br.com.gok.templateapi.exception.InternalServerErrorException;
import br.com.gok.templateapi.mock.MockPlanet;
import br.com.gok.templateapi.model.Planet;
import br.com.gok.templateapi.repository.PlanetRepository;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PlanetServiceTests {
    
    @InjectMocks
    private PlanetService service;

    @Mock
    private PlanetRepository repository;

    @Mock
    private PlanetClient client;

    @Test
    public void listSuccessTest(){
        Iterable<Planet> planetsExpected = MockPlanet.arrayCompletePlanet();
        when(repository.findAll()).thenReturn(planetsExpected);
        Iterable<Planet> planetsActual = service.list();
        assertEquals(planetsExpected, planetsActual);

        verify(repository, times(1)).findAll();
    }

    @Test
    public void findByIdSuccessTest() throws Exception{
        Planet planetExpected = MockPlanet.completePlanet();
        when(repository.findById(MockPlanet.ID)).thenReturn(MockPlanet.optionalPlanet());
        Planet planetActual = service.findById(MockPlanet.ID);
        assertEquals(planetExpected, planetActual);
    }

    @Test
    public void filterByNameTest(){
        List<Planet> planetsExpected = MockPlanet.arrayCompletePlanet();
        when(repository.filterByName(MockPlanet.NAME)).thenReturn(MockPlanet.arrayCompletePlanet());
        List<Planet> planetsActual = service.filterByName(MockPlanet.NAME);
        assertEquals(planetsExpected, planetsActual);

        verify(repository, times(1)).filterByName(MockPlanet.NAME);
    }


    @Test
    public void filterByPopulationTest(){
        List<Planet> planetsExpected = MockPlanet.arrayCompletePlanet();
        when(repository.filterByPopulation(MockPlanet.POPULATION, MockPlanet.POPULATION)).thenReturn(MockPlanet.arrayCompletePlanet());
        List<Planet> planetsActual = service.filterByPopulation(MockPlanet.POPULATION, MockPlanet.POPULATION);
        assertEquals(planetsExpected, planetsActual);

        verify(repository, times(1)).filterByPopulation(MockPlanet.POPULATION, MockPlanet.POPULATION);
    }

    @Test
    public void filterByPopulationMinGreaterMaxErrorTest(){
        try {
            service.filterByPopulation(10L, 0L);
        } catch (InternalServerErrorException e) {
            assertThat(e.getMessage(), is(e.getMessage()));
        }
    }
    @Test
    public void savePlanetsErrorTest() throws Exception{
        try {
            service.savePlanets(Collections.singletonList(MockPlanet.incompletePlanetDTO()));
        } catch (Exception e) {
            assertThat(e.getMessage(), is(e.getMessage()));
        }
    }
    @Test
    public void savePlanetsTest() throws Exception{
        service.savePlanets(MockPlanet.arrayCompletePlanetDTO());
    }

    @Test
    public void deleteById() throws Exception {
        service.deleteByid(MockPlanet.ID);
        verify(repository, times(1)).deleteById(MockPlanet.ID);
    }

}
