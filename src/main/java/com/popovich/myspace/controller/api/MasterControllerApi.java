package com.popovich.myspace.controller.api;

import com.popovich.myspace.entity.Master;
import com.popovich.myspace.service.MySpaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/masters")
public class MasterControllerApi {

    private final MySpaceService mySpaceService;

    @Autowired
    public MasterControllerApi(MySpaceService mySpaceService){
        this.mySpaceService = mySpaceService;
    }

    @PostMapping("/add")
    public void addMaster(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        mySpaceService.addMaster(name, age);
    }

    @PostMapping("/assign")
    public void assignMasterToPlanet(@RequestParam("mastername") String mastername, @RequestParam("planetname") String planetname) {
        mySpaceService.assignMasterToPlanet(mastername, planetname);
    }

    @GetMapping("/youngest")
    public List<Master> getTop10YoungestMasters(){
        return mySpaceService.findTopTenYoungMasters();
    }

    @GetMapping("/slackers")
    public List<Master> getSlackingMasters(){
        return mySpaceService.findTopTenYoungMasters();
    }

}
