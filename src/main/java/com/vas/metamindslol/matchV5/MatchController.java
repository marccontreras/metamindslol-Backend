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
    //actualitzar-ho a put en el futur
    @GetMapping(value = "/match/{region}/recentMatch/summoner/{summonerName}")
    public String loadMostRecentMatchBySummonerName(@PathVariable String region, @PathVariable String summonerName){
        return service.loadMatchBySummonerName(region,summonerName);
    }

    @GetMapping(value = "/match/{region}/matches/summoner/{summonerName}")
    public String loadUntilFoundMatchBySummonerName(@PathVariable String region, @PathVariable String summonerName){
        return service.loadUntilFoundMatchBySummonerName(region,summonerName,null);
    }
    //fi dels metodes put
    @GetMapping(value = "/match/{region}/getRecentMatch/{summonerName}")
    public String getMostRecentMatchBySummonerName(@PathVariable String region, @PathVariable String summonerName){
        return service.getMostRecentMatchBySummonerName(region,summonerName);
    }
    @GetMapping(value = "/match/{region}/summoner/{summonerName}")
    public String getMatchesBySummonerName(@PathVariable String region,@PathVariable String summonerName){
        return service.getMatchesBySummonerName(region,summonerName);
    }

    @GetMapping(value = "/match/{region}/{summoner}/{matchNumber}")
    public String getMatchBySummoner(@PathVariable String region,@PathVariable String summoner,@PathVariable Integer matchNumber){
        return service.getMatchBySummoner(region, gson.fromJson(summoner,Summoner.class),matchNumber);
    }

    //works if a summoner is given
    /*
    @GetMapping(value = "/match/{region}/recentMatch/{summoner}")
    public String getMostRecentMatchBySummoner(@PathVariable String region,@PathVariable String summoner){
        return service.loadMostRecentMatchBySummoner(region,gson.fromJson(summoner,Summoner.class));
    }
*/
}
