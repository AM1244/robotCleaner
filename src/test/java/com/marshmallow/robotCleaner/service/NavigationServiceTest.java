package com.marshmallow.robotCleaner.service;

import com.marshmallow.robotCleaner.exception.OutOfAreaException;
import com.marshmallow.robotCleaner.model.Instructions;
import com.marshmallow.robotCleaner.model.ResponseModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static com.marshmallow.robotCleaner.TestUtils.createInstructionsDto;
import static com.marshmallow.robotCleaner.TestUtils.createWrongInstructionsDto;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NavigationServiceTest {
    @Autowired
    private NavigationService navigationService;

    @Test
    public void test_when_given_correct_instructions_expect_valid_response() {

        Instructions instructions = createInstructionsDto(5,5);

        ResponseModel responseModel = navigationService.navigate(instructions);

        assertNotNull(responseModel);
        Integer oilsCleaned = responseModel.getOilPatchesCleaned();
        List<Integer> position = responseModel.getFinalPosition();
        assertEquals(1, oilsCleaned);
        assertEquals(Arrays.asList(1, 3), position);
    }

    @Test(expected = OutOfAreaException.class)
    public void test_when_given_oil_outside_of_area_expect_OutOfAreaException_exception(){
        Instructions instructions = createWrongInstructionsDto(5,5 );
        navigationService.navigate(instructions);
    }
}
