package com.vas.metamindslol.summoner;

import com.vas.metamindslol.exception.NotFoundException;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.vas.metamindslol.GsonInstance.gson;
import static com.vas.metamindslol.R4JInstance.loLAPI;

@Service
public class SummonerService {

    /**
     * @param region
     * @param summonerName
     * @return The item asked by parameter
     */
    public String getSummonerByName(String region, String summonerName) {


        String shard = "";
        Optional<LeagueShard> opShard = LeagueShard.fromString(region);
        Summoner summoner = null;
        if (opShard.isPresent())
            summoner = loLAPI.getSummonerAPI().getSummonerByName(opShard.get(), summonerName);
        return gson.toJson(Objects.requireNonNullElse(summoner,new NotFoundException().getMessage()));


    }
}


