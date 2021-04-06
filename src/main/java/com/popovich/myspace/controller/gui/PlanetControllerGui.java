package com.popovich.myspace.controller.gui;

import com.popovich.myspace.entity.Planet;
import com.popovich.myspace.service.MySpaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("gui/planets")
public class PlanetControllerGui {

    private final MySpaceService mySpaceService;

    @Autowired
    public PlanetControllerGui(MySpaceService mySpaceService){
        this.mySpaceService = mySpaceService;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("planets", mySpaceService.getAllPlanets());
        return "planets/index";
    }
    @GetMapping("/{id}")
    public String showPlanet(@PathVariable("id") Long id, Model model){
        model.addAttribute("planet", mySpaceService.getPlanetById(id));
        return "planets/show";
    }

    @GetMapping("/new")
    public String newPlanet(Model model){
        model.addAttribute("planet", new Planet());
        return "planets/new";
    }

    @PostMapping("/create")
    public String newPlanet(@ModelAttribute("planet") Planet planet ){
        mySpaceService.addPlanetByName(planet.getName());
        return "redirect:/gui/planets";
    }

    @DeleteMapping("/{id}")
    public String delete(@ModelAttribute("planet") Planet planet) {
        mySpaceService.deletePlanet(planet);
        return "redirect:/gui/planets";

    }
    @PostMapping("/{id}/assign")
    public String assign(@RequestParam("name") String planetName, @RequestParam("master_name") String masterName) {
        mySpaceService.assignMasterToPlanet(masterName, planetName);
        return "redirect:/gui/planets";
    }
}
