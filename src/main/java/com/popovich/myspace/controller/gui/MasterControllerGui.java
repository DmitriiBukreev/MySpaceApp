package com.popovich.myspace.controller.gui;

import com.popovich.myspace.entity.Master;
import com.popovich.myspace.service.MySpaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("gui/masters")
public class MasterControllerGui {

    private final MySpaceService mySpaceService;

    @Autowired
    public MasterControllerGui(MySpaceService mySpaceService){
        this.mySpaceService = mySpaceService;
    }

    @GetMapping("/new")
    public String newMaster(Model model){
        model.addAttribute("master", new Master());
        return "masters/new";
    }

    @PostMapping("/create")
    public String createMaster(@ModelAttribute("master") Master master){
        mySpaceService.addMaster(master.getName(),master.getAge());
        return "redirect:/gui/masters";
    }

    @GetMapping("/slackers")
    public String getSlackers(Model model) {
        model.addAttribute("slackers", mySpaceService.findAllSlackerMasters());
        return "masters/slackers";
    }

    @GetMapping("/youngest")
    public String getYoungestMasters(Model model){
        model.addAttribute("youngest", mySpaceService.findTopTenYoungMasters());
        return "masters/youngest";
    }

    @GetMapping("")
    public String showAllMasters(Model model) {
        model.addAttribute("masters", mySpaceService.getAllMasters());
        return "masters/index";
    }
}
