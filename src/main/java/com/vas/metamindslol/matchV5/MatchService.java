package com.vas.metamindslol.matchV5;

import com.vas.metamindslol.ModelMapperConfig;
import com.vas.metamindslol.R4JInstance;
import com.vas.metamindslol.exception.NotFoundException;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.pojo.lol.match.v5.LOLMatch;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.vas.metamindslol.GsonInstance.gson;

@Service
public class MatchService {

    @Autowired
    private ModelMapperConfig modelMapper;

    @Autowired
    MatchRepository matchRepository;
//LOADING RECENT GAME INTO DB
    /**
     * @param region
     * @param summonerName
     * @return The most recent match of the given summoner if it exists
     */
    public String loadMostRecentMatchBySummonerName(String region, String summonerName) {

        Optional<LeagueShard> opShard = LeagueShard.fromString(region);
        Summoner summoner;
        LOLMatch match = null;
        LOLMatchDD matchDD=null;
        if (opShard.isPresent()) {
            summoner = R4JInstance.loLAPI.getSummonerAPI().getSummonerByName(opShard.get(), summonerName);
            List<String> matches = summoner.getLeagueGames().get();
            match = LOLMatch.get(opShard.get(), matches.get(0));
            matchDD = findOrSaveMatch(match);
        }
        return gson.toJson(Objects.requireNonNullElse(matchDD, new NotFoundException().getMessage()));

    }

    /**
     * returns all the loaded summoner names in the db given its name
     * @param region
     * @param summonerName
     * @return The match mapped to matchDB
     */
    public String getMatchesBySummonerName(String region, String summonerName) {
        Optional<LeagueShard> opShard = LeagueShard.fromString(region);
        List<LOLMatchDD>matchesDB=null;
        if (opShard.isPresent()) {
            //make a method that finds by matchParticipant, summonerName(maybe puuid)
            matchesDB=matchRepository.getMatchesBySummonerName(summonerName);
        }
        return gson.toJson(Objects.requireNonNullElse(matchesDB, new NotFoundException().getMessage()));

    }




    //NEEDED A GET
    // AND A LOAD UNTIL THE MOST RECENT IN DB


    /**
     * @implNote if the match is not on DB it's added
     * @param match
     * @return The match mapped to matchDB
     */
    private LOLMatchDD findOrSaveMatch(LOLMatch match) {
        LOLMatchDD matchDD;
        Optional<LOLMatchDD> opMatch = matchRepository.findById(match.getGameId());
        matchDD = modelMapper.map(match, LOLMatchDD.class);
        if (opMatch.isEmpty())
            matchDD= matchRepository.save(matchDD);
        return matchDD;
    }



//LOADING BY SUMMONER FOR NOW WON'T BE USED
    /**
     * @param region
     * @param summoner
     * @return The most recent match of the given summoner
     */
    public String loadMostRecentMatchBySummoner(String region, Summoner summoner) {
        List<String> matches = summoner.getLeagueGames().get();

        LOLMatch match = null;
        LOLMatchDD matchDD = null;
        Optional<LeagueShard> opShard = LeagueShard.fromString(region);
        if (opShard.isPresent()) {
            match = LOLMatch.get(opShard.get(), matches.get(0));
            matchDD = findOrSaveMatch(match);
        }
        return gson.toJson(Objects.requireNonNullElse(matchDD, new NotFoundException().getMessage()));

    }


    /**
     * @param region
     * @param summoner
     * @return A match of the given summoner
     */
    public String loadMatchBySummoner(String region, Summoner summoner, Integer matchNumber) {
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


