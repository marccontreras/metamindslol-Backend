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

//TODO: SERIALIZE FROM STRING TO LEAGUESHARD, AND MAKE THE GSON SUMMONER CALLS IN THE SERVICE
    @GetMapping(value = "/match/{region}/recentMatch/summoner/{summonerName}")
    public String getMostRecentMatchBySummonerName(@PathVariable String region,@PathVariable String summonerName){
        return service.loadMostRecentMatchBySummonerName(region,summonerName);
    }
    @GetMapping(value = "/match/{region}/summoner/{summonerName}")
    public String getMatchesBySummonerName(@PathVariable String region,@PathVariable String summonerName){
        return service.getMatchesBySummonerName(region,summonerName);
    }

    //try it
    @GetMapping(value = "/match/{region}/recentMatch/{summoner}")
    public String getMostRecentMatchBySummoner(@PathVariable String region,@PathVariable String summoner){
        return service.loadMostRecentMatchBySummoner(region,gson.fromJson(summoner,Summoner.class));
    }

    @GetMapping(value = "/match/{region}/{summoner}/{matchNumber}")
    public String getMatchBySummoner(@PathVariable String region,@PathVariable String summoner,@PathVariable Integer matchNumber){
        return service.loadMatchBySummoner(region, gson.fromJson(summoner,Summoner.class),matchNumber);
    }

}
