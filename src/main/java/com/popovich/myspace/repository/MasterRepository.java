package com.popovich.myspace.repository;

import com.popovich.myspace.entity.Master;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MasterRepository extends CrudRepository<Master, Long> {
    Optional<Master> findByName(String name);
    List<Master> findTop10ByOrderByAgeAsc();
    List<Master> findByPlanetsIsNull();
    List<Master> findAll();
}
