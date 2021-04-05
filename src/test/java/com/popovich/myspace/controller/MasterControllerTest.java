package com.popovich.myspace.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.popovich.myspace.controller.api.MasterControllerApi;
import com.popovich.myspace.entity.Master;
import com.popovich.myspace.entity.Planet;
import com.popovich.myspace.service.MySpaceService;
import com.popovich.myspace.service.exception.EmptyInputStringException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MasterControllerApi.class)
public class MasterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MySpaceService service;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void testValidAddingProcessedCorrectly() throws Exception {

        doNothing().when(service).addMaster(any(), any());

        mockMvc.perform(put("/master/add?name=JUSTIN&age=56"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testInvalidAddingProcessedCorrectly() throws Exception {

        doThrow(new EmptyInputStringException()).when(service).addMaster(any(), any());

        mockMvc.perform(put("/master/add?name=JUSTIN&age="))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    void testCanFindTop10YoungestMasters() throws Exception {

        when(service.findTopTenYoungMasters())
                .thenReturn(getAllMasters());

        mockMvc.perform(get("/master/youngest")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(getAllMasters())))
                .andReturn();
    }
    @Test
    void testCanFindSlackerMasters() throws Exception {

        when(service.findAllSlackerMasters())
                .thenReturn(getSlackers());

        mockMvc.perform(get("/master/slackers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(getSlackers())))
                .andReturn();
    }

    private List<Master> getAllMasters() {
        var masterOne = new Master("Bard", 2000);
        var masterTwo = new Master("Victor", 50);
        var masterThree = new Master("Vova", 100);
        var planet = new Planet("Earth");
        planet.setMaster(masterOne);
        return List.of(masterOne, masterTwo, masterThree);
    }
    private List<Master> getSlackers(){
        return List.of(new Master("Victor", 50), new Master("Vova", 100));
    }
}
