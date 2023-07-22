package com.vas.metamindslol.summoner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SummonerController {
    @Autowired
    SummonerService service;


    @GetMapping(value = "/summoners/{region}/{summonerName}")
    public String getSummonerByName(@PathVariable String region,@PathVariable String summonerName){
        return service.getSummonerByNameAndRegion(region,summonerName);
    }

    @GetMapping(value = "/summoners/search/{summoners}")
    public String getSummonerByName(@PathVariable String summoners){
        return service.getSummonerByName(summoners);
    }

}
