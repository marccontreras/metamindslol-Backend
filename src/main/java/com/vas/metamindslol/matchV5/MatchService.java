package com.vas.metamindslol.matchV5;

import com.vas.metamindslol.ModelMapperConfig;
import com.vas.metamindslol.R4JInstance;
import com.vas.metamindslol.Timeline.TimelineRepository;
import com.vas.metamindslol.Timeline.TimelineService;
import com.vas.metamindslol.exception.NotFoundException;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.impl.lol.builders.matchv5.match.MatchListBuilder;
import no.stelar7.api.r4j.pojo.lol.match.v5.LOLMatch;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    TimelineService timelineService;
//LOADING RECENT GAME INTO DB

    /**
     * @param region
     * @param summonerName
     * @return The most recent match of the given summoner if it exists
     */
    public String loadMatchBySummonerName(String region, String summonerName) {

        Optional<LeagueShard> opShard = LeagueShard.fromString(region);
        Summoner summoner;
        LOLMatch match = null;
        LOLMatchDD matchDD = null;
        if (opShard.isPresent()) {
            summoner = R4JInstance.loLAPI.getSummonerAPI().getSummonerByName(opShard.get(), summonerName);
            List<String> matches = summoner.getLeagueGames().get();
            match = LOLMatch.get(opShard.get(), matches.get(0));
            matchDD = findOrSaveMatch(match);
        }
        return gson.toJson(Objects.requireNonNullElse(matchDD, new NotFoundException().getMessage()));

    }

    /**
     * @param region
     * @param summonerName
     * @return The most recent match of the given summoner if it exists
     */
    public String loadMatchBySummonerName(String region, String summonerName, Integer count,Integer gameNumber) {
        if (count == null) {
            count = 20;
            gameNumber = 0;
        }
        Optional<LeagueShard> opShard = LeagueShard.fromString(region);
        Summoner summoner;
        LOLMatch match = null;
        LOLMatchDD matchDD = null;
        if (opShard.isPresent()) {
            summoner = R4JInstance.loLAPI.getSummonerAPI().getSummonerByName(opShard.get(), summonerName);
            //add the start to be able to get games after the 100 first
            List<String> matches =  new MatchListBuilder().withPlatform(summoner.getPlatform()).withPuuid(summoner.getPUUID()).withCount(count).get();
            match = LOLMatch.get(opShard.get(), matches.get(gameNumber));
            matchDD = findOrSaveMatch(match);
            timelineService.loadTimelineByGameId(region, matchDD.getGameId());
        }
        return gson.toJson(Objects.requireNonNullElse(matchDD, new NotFoundException().getMessage()));

    }

    /**
     * returns all the loaded matches in the db given its summonerName
     *
     * @param region
     * @param summonerName
     * @return The match mapped to matchDB
     */
    public String getMatchesBySummonerName(String region, String summonerName) {
        Optional<LeagueShard> opShard = LeagueShard.fromString(region);
        List<LOLMatchDD> matchesDB = null;
        if (opShard.isPresent()) {
            //make a method that finds by matchParticipant, summonerName(maybe puuid)
            matchesDB = matchRepository.getMatchesBySummonerName(summonerName);
        }
        return gson.toJson(Objects.requireNonNullElse(matchesDB, new NotFoundException().getMessage()));

    }

    /**
     * returns the most recent match loaded in the db given its summoner name
     *
     * @param region
     * @param summonerName
     * @return The match mapped to matchDB
     */
    public String getMostRecentMatchBySummonerName(String region, String summonerName) {
        Optional<LeagueShard> opShard = LeagueShard.fromString(region);
        LOLMatchDD matchesDB = null;
        if (opShard.isPresent()) {
            //make a method that finds by matchParticipant, summonerName(maybe puuid)
            matchesDB = matchRepository.getMostRecentMatchBySummonerName(summonerName);
        }
        return gson.toJson(Objects.requireNonNullElse(matchesDB, new NotFoundException().getMessage()));

    }

    /**
     * loads the new matches of the summonerName
     *
     * @param region
     * @param summonerName
     * @return The new matches
     */
    public String loadUntilFoundMatchBySummonerName(String region, String summonerName) {
        LOLMatchDD match;
        long matchId = 0;

        //we have atleast a game in the db
        String matchString = getMostRecentMatchBySummonerName(region, summonerName);
        if (!matchString.equals(gson.toJson(new NotFoundException().getMessage(),String.class))) {
            match = gson.fromJson(matchString, LOLMatchDD.class);
            matchId=match.getGameId();
        }

        List<LOLMatchDD> matchesDB = new ArrayList<>();
        LOLMatchDD matchTemp=null;
        int count = 0;
        // deal with the start for now will show only a page which is 100
        int maxGamesAllowed=100;
        boolean found = false;

        while (!found && count<maxGamesAllowed) {
            matchString= loadMatchBySummonerName(region, summonerName,maxGamesAllowed, count);
            if (!matchString.equals(new NotFoundException().getMessage())) {
                matchTemp = gson.fromJson(matchString, LOLMatchDD.class);
                timelineService.loadTimelineByGameId(region, matchTemp.getGameId());
                if (matchTemp.getGameId() == matchId)
                    found = true;
                matchesDB.add(matchTemp);
            }else if(count==0)
                gson.toJson(new NotFoundException("games"));//return games not found
            else
                found=true;

            count++;
        }
        return gson.toJson(Objects.requireNonNullElse(matchesDB, new NotFoundException().getMessage()));

    }


    /**
     * @param match
     * @return The match mapped to matchDB
     * @implNote if the match is not on DB it's added
     */
    private LOLMatchDD findOrSaveMatch(LOLMatch match) {
        LOLMatchDD matchDD;
        Optional<LOLMatchDD> opMatch = matchRepository.findById(match.getGameId());
        matchDD = modelMapper.map(match, LOLMatchDD.class);
        if (opMatch.isEmpty())
            matchDD = matchRepository.save(matchDD);
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


