package com.vas.metamindslol.matchV5;

import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.vas.metamindslol.GsonInstance.gson;

@RestController
public class MatchController {
    @Autowired
    MatchService service;


    @GetMapping(value = "/match/{region}/matches/summoner/{summonerName}")
    public String loadUntilFoundMatchBySummonerName(@PathVariable String region, @PathVariable String summonerName){
        return service.loadUntilFoundMatchBySummonerName(region,summonerName,null);
    }

    @GetMapping(value = "/match/{region}/summoner/{summonerName}")
    public String getMatchesBySummonerName(@PathVariable String region,@PathVariable String summonerName){
        return service.getMatchesBySummonerName(region,summonerName);
    }



}
