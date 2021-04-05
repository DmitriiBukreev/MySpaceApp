package com.popovich.myspace.service;

import com.popovich.myspace.entity.Master;
import com.popovich.myspace.entity.Planet;
import com.popovich.myspace.repository.MasterRepository;
import com.popovich.myspace.repository.PlanetRepository;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;



import java.util.Optional;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MySpaceServiceTest {

    @MockBean
    private MasterRepository masterRepository;

    @MockBean
    private PlanetRepository planetRepository;

    @Autowired
    private MySpaceService service;

    @Test
    void testCanAddPlanetWithValidName() {
        service.addPlanetByName("Earth");
        verify(planetRepository).save(new Planet("Earth"));

    }

    @Test
    void testCanAddMasterWithValidName() {
        service.addMaster("Ostap", 6);
        verify(masterRepository).save(new Master("Ostap", 6));

    }

    @Test
    void testCanDeletePlanetWithValidName() {
        var planet = new Planet("Earth");
        when(planetRepository.findByName("Earth")).thenReturn(Optional.of(planet));
        service.deletePlanetByNameObject(planet);
        verify(planetRepository).deleteByName("Earth");
    }

    @Test
    void assignMasterToPlanetTest() {
        var master = new Master("Ivan", 44);
        var planet = new Planet("Earth");
        when(masterRepository.findByName("Ivan")).thenReturn(Optional.of(master));
        when(planetRepository.findByName("Earth")).thenReturn(Optional.of(planet));
        service.assignMasterToPlanet("Ivan", "Earth");
        verify(planetRepository).save(planet);
    }
}
