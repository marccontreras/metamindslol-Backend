package com.vas.metamindslol.match;

import com.vas.metamindslol.exception.NotFoundException;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.pojo.lol.match.v5.LOLMatch;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.vas.metamindslol.GsonInstance.gson;
import static com.vas.metamindslol.R4JInstance.loLAPI;

@Service
public class MatchService {

    /**
     * @param region
     * @param summonerName
     * @return The most recent match of the given summoner if it exists
     */
    public String getMostRecentMatchBySummonerName(String region, String summonerName) {

        Optional<LeagueShard> opShard = LeagueShard.fromString(region);
        Summoner summoner;
        LOLMatch match=null;
        if (opShard.isPresent()) {
            summoner = loLAPI.getSummonerAPI().getSummonerByName(opShard.get(), summonerName);
            List<String> matches = summoner.getLeagueGames().get();
             match = LOLMatch.get(opShard.get(), matches.get(0));
        }
        return gson.toJson(Objects.requireNonNullElse(match, new NotFoundException().getMessage()));

    }

    /**
     * @param region
     * @param summoner
     * @return The most recent match of the given summoner
     */
    public String getMostRecentMatchBySummoner(String region, Summoner summoner) {
        List<String> matches = summoner.getLeagueGames().get();

        LOLMatch match = null;
        Optional<LeagueShard> opShard = LeagueShard.fromString(region);
        if (opShard.isPresent()) {
            match = LOLMatch.get(opShard.get(), matches.get(0));
        }
        return gson.toJson(Objects.requireNonNullElse(match, new NotFoundException().getMessage()));

    }

    /**
     * @param region
     * @param summoner
     * @return A match of the given summoner
     */
    public String getMatchBySummoner(String region, Summoner summoner,Integer matchNumber) {
        //limited to the last 20 for the moment, afterwards look if there's another way to get more
        List<String> matches = summoner.getLeagueGames().get();
        LOLMatch match = null;
        Optional<LeagueShard> opShard = LeagueShard.fromString(region);
        if (opShard.isPresent()) {
            match = LOLMatch.get(opShard.get(), matches.get(matchNumber));
        }
        return gson.toJson(Objects.requireNonNullElse(match, new NotFoundException().getMessage()));

    }

}


