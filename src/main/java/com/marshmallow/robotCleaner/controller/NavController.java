package com.marshmallow.robotCleaner.controller;

import com.marshmallow.robotCleaner.model.Instructions;
import com.marshmallow.robotCleaner.model.ResponseModel;
import com.marshmallow.robotCleaner.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class NavController {

    private NavigationService navigationService;

    @Autowired
    public NavController(final NavigationService navigationService) {
        this.navigationService = navigationService;
    }


    @PostMapping("/navigation")
    public ResponseEntity<ResponseModel> navigation(@Valid @RequestBody final Instructions instructions){
        ResponseModel responseModel = navigationService.navigate(instructions);

        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }
}
