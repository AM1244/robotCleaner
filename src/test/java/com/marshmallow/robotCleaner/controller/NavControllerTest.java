package com.marshmallow.robotCleaner.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marshmallow.robotCleaner.TestUtils;
import com.marshmallow.robotCleaner.exception.OutOfAreaException;
import com.marshmallow.robotCleaner.model.*;
import com.marshmallow.robotCleaner.service.NavigationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;


public class NavControllerTest {

    public MockMvc mockMvc;

    @Mock
    private NavigationService navigationService;

    private NavController navController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
//        navigationService = new NavigationService();
        navController = new NavController(navigationService);
        mockMvc = MockMvcBuilders.standaloneSetup(navController).build();
    }


    @Test
    public void test_When_ValidRequest_Expect_Success() throws Exception {
        Instructions instructions = TestUtils.createInstructionsDto(5,5);
        Coordinates coordinates = new Coordinates(Arrays.asList(5, 5));
        Position position = new Position(Arrays.asList(1, 2), coordinates);

        Integer oilsPatched = 1;
        ResponseModel responseModel = new ResponseModel(position,oilsPatched);

        when(navigationService.navigate(instructions)).thenReturn(responseModel);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/navigation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(instructions))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(navigationService).navigate(any(Instructions.class));
    }

    @Test (expected = OutOfAreaException.class)
    public void when_request_has_invalid_starting_position_then_expect_OutOfAreaException() {
        NavigationService navigationService = new NavigationService();
        Instructions instructions = Instructions.builder()
                .areaSize(Arrays.asList(3, 3))
                .startingPosition(Arrays.asList(1, 5))
                .oilPatches(Arrays.asList(Arrays.asList(1, 2)))
                .navigationInstructions("NNESEESWNWW")
                .build();

        navigationService.navigate(instructions);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
