package com.popovich.myspace.repository;

import com.popovich.myspace.entity.Master;
import com.popovich.myspace.entity.Planet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PlanetRepositoryTest {

    @Autowired
    private PlanetRepository repository;


    @Test
    void testCanSaveAndFindPlanetByName() {
        var planet = new Planet("Earth");
        repository.save(planet);

        var savedPlanet = repository.findByName("Earth").get();
        assertNotNull(savedPlanet.getId());
    }

    @Test
    void testCanDeletePlanetByName() {
        var planet = new Planet("Earth");
        repository.save(planet);

        repository.delete(repository.findByName("Earth").get());
        assertTrue(repository.findByName("Earth").isEmpty());
    }


}