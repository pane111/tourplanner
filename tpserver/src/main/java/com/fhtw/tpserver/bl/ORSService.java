package com.fhtw.tpserver.bl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.Value;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.mapping.Map;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ORSService {

    private final RestTemplate restTemplate;
    private String apiKey="5b3ce3597851110001cf62484a0735536d15427f8b3a45b2671fe83a";
    Logger logger = LogManager.getLogger(ORSService.class);


    public ORSService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        logger.info("API Key: " + apiKey);
    }

    public JsonNode getDirections(String start, String end)
    {
        String url = "https://api.openrouteservice.org/v2/directions/foot-walking?api_key=" + apiKey +
                "&start=" + start + "&end=" + end;
        logger.info("Request: "+url);
        String res = restTemplate.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(res);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    public JsonNode getGeocode(String locText)
    {
        String url = "https://api.openrouteservice.org/geocode/search?api_key="+apiKey+
                "&text="+locText;
        logger.info("Request: "+url);
        String res = restTemplate.getForObject(url, String.class);
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(res);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
