package com.techelevator.services;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.techelevator.dao.ItineraryDao;
import com.techelevator.model.Itinerary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestMapboxService implements MapboxService{
    // CatCards - Mod 2 final review
    private JdbcTemplate jdbcTemplate;
    private final ItineraryDao itineraryDao;
    private RestTemplate restTemplate = new RestTemplate();

    public RestMapboxService(ItineraryDao itineraryDao, JdbcTemplate jdbcTemplate){
        this.itineraryDao = itineraryDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void sendMapBoxRequest(Itinerary itinerary) throws RestClientResponseException {
//        //System.out.println(itineraryDao.constructMBoxAPIURL(itinerary));
//        String finalURL = itineraryDao.constructMBoxAPIURL(itinerary);
//        JSONPObject datadump = restTemplate.getForObject(finalURL, JSONPObject.class);
    }


}
