package com.popovich.myspace.service;

import com.popovich.myspace.entity.Master;
import com.popovich.myspace.entity.Planet;
import com.popovich.myspace.repository.MasterRepository;
import com.popovich.myspace.repository.PlanetRepository;
import com.popovich.myspace.service.exception.NameIsAlreadyTakenException;
import com.popovich.myspace.service.exception.NoSuchObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;


@Service
public class MySpaceService {

    private final MasterRepository masterRepository;
    private final PlanetRepository planetRepository;

    private static final Pattern namingPattern = Pattern.compile(namingRegexp());

    @Autowired
    public MySpaceService(MasterRepository masterRepository, PlanetRepository planetRepository){
        this.masterRepository = masterRepository;
        this.planetRepository = planetRepository;
    }

    public void addPlanetByName(String name) {
        var planetOptional = planetRepository.findByName(name);
        if (!name.matches(namingRegexp())){
            throw new IllegalArgumentException();
        }
        if (planetOptional.isPresent()) {
            throw new NameIsAlreadyTakenException();
        }
        planetRepository.save(new Planet(name));
    }

    public void deletePlanet(Planet planet) {
        var planetOptional = planetRepository.findByName(planet.getName());
        if (planetOptional.isPresent()) {
            throw new NoSuchObjectException();
        }
        planetRepository.delete(planet);
    }

    public void addMaster(String name, Integer age) {

        if (!name.matches(namingRegexp()) || age < 1){
            throw new IllegalArgumentException();
        }
        var masterOptional = masterRepository.findByName(name);

        if (masterOptional.isPresent()) {
            throw new NameIsAlreadyTakenException();
        }
        masterRepository.save(new Master(name, age));
    }


    public void assignMasterToPlanet(String masterName, String planetName) {

        var masterOptional = masterRepository.findByName(masterName);
        var planetOptional = planetRepository.findByName(planetName);
        if (masterOptional.isEmpty() || planetOptional.isEmpty()) {
            throw new NoSuchObjectException();
        }
        var master = masterOptional.get();
        var planet = planetOptional.get();
        planet.setMaster(master);
        planetRepository.save(planet);
    }

    public void deletePlanetByNameObject(Planet planet) {
        var planetOptional = planetRepository.findByName(planet.getName());
        if (planetOptional.isEmpty()) {
            throw new NoSuchObjectException();
        }
        planetRepository.deleteByName(planet.getName());
    }

    public void deletePlanetByName(String name) {
        var planetOptional = planetRepository.findByName(name);
        if (planetOptional.isEmpty()) {
            throw new NoSuchObjectException();
        }
        planetRepository.deleteByName(name);
    }

    public List<Master> findAllSlackerMasters() {
        return masterRepository.findByPlanetsIsNull();
    }

    public List<Master> findTopTenYoungMasters() {
        return masterRepository.findTop10ByOrderByAgeAsc();
    }

    public List <Planet> getAllPlanets() {
        return planetRepository.findAll();
    }

    public Planet getPlanetById(Long id) {
        return planetRepository.findById(id).get();
    }

    public List <Master> getAllMasters() {
        return masterRepository.findAll();
    }
    public Master getMasterById(Long id){
        return masterRepository.findById(id).get();
    }

    private static String namingRegexp() {
        return "\\p{L}+\\d*";
    }
}
