package com.fhtw.tpserver.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fhtw.tpserver.bl.ORSService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ors")
public class ORSController {

    private final ORSService orsService;


    public ORSController(ORSService orsService) {
        this.orsService = orsService;
    }

    @GetMapping("/directions")
        public JsonNode getDirections(@RequestParam String start, @RequestParam String end) {
        return orsService.getDirections(start, end);
    }
    @GetMapping("/geocode")
    public JsonNode searchCoords(@RequestParam String text) {
        return orsService.getGeocode(text);
    }
}
