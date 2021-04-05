package com.popovich.myspace.repository;

import com.popovich.myspace.entity.Master;
import com.popovich.myspace.entity.Planet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class MasterRepositoryTest {

    @Autowired
    private PlanetRepository planetrepository;

    @Autowired
    private MasterRepository masterRepository;

    @Test
    void testCanSaveAndFindMasterByName() {
        var master = new Master("Vova", 4);
        masterRepository.save(master);

        var savedMaster = masterRepository.findByName("Vova").get();
        assertNotNull(savedMaster.getId());
    }


    @Test
    void testCanAssignMasterToPlanet() {
        var master = new Master("Raven",1000);
        master = masterRepository.save(master);

        var planet = new Planet("Earth");
        planet = planetrepository.save(planet);

        planet.setMaster(master);

        var savedPlanet = planetrepository.findByName("Earth");
        assertEquals("Raven", savedPlanet.get().getMaster().getName());
        assertEquals(1000, savedPlanet.get().getMaster().getAge());
    }


}
