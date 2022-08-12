package com.example.owpprojekat.api.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping(value = "/api/test",
            produces = MediaType.APPLICATION_JSON_VALUE)
    String getTest() {
        return "{'value' = 'test';}";
    }

}
