package com.popovich.myspace.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.popovich.myspace.controller.api.PlanetControllerApi;
import com.popovich.myspace.service.MySpaceService;
import com.popovich.myspace.service.exception.EmptyInputStringException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlanetControllerApi.class)
public class PlanetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MySpaceService service;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void testValidAddingProcessedCorrectly() throws Exception {

        doNothing().when(service).addPlanetByName(any());

        mockMvc.perform(put("/planet/add?name=qwerty"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testInvalidAddingProcessedCorrectly() throws Exception {
        doThrow(new EmptyInputStringException()).when(service).addPlanetByName(any());

        mockMvc.perform(put("/planet/add?name="))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    void testValidDeletingProcessedCorrectly() throws Exception {

        doNothing().when(service).deletePlanetByName(any());

        mockMvc.perform(put("/planet/{name}/delete", "name"))
                .andExpect(status().isOk())
                .andReturn();
    }

}
