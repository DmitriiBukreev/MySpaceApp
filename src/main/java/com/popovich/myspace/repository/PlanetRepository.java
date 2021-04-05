package com.popovich.myspace.repository;

import com.popovich.myspace.entity.Planet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlanetRepository extends CrudRepository<Planet, Long> {
    Optional<Planet> findByName(String name);

    @Transactional
    void deleteByName(String name);

    @Transactional
    void deletePlanetById(Long id);

    List<Planet> findAll();
}
