package com.popovich.myspace.controller.api;

import com.popovich.myspace.service.MySpaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/planets")
public class PlanetControllerApi {

    private final MySpaceService mySpaceService;

    @Autowired
    public PlanetControllerApi(MySpaceService mySpaceService){
        this.mySpaceService = mySpaceService;
    }

    @PostMapping()
    public void addPlanet(@RequestParam("name") String name) {
        mySpaceService.addPlanetByName(name);
    }
    @DeleteMapping("/{id}/delete")
    public void deletePlanet(@RequestParam("name") String name) {
        mySpaceService.deletePlanetByName(name);
    }
}
