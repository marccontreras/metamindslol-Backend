package com.vas.metamindslol.matchV5;

import com.google.gson.Gson;
import com.vas.metamindslol.GsonInstance;
import com.vas.metamindslol.ModelMapperConfig;
import com.vas.metamindslol.R4JInstance;
import com.vas.metamindslol.exception.NotFoundException;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.lol.SummonerSpellType;
import no.stelar7.api.r4j.impl.lol.builders.matchv5.match.MatchListBuilder;
import no.stelar7.api.r4j.pojo.lol.match.v5.LOLMatch;
import no.stelar7.api.r4j.pojo.lol.match.v5.MatchTeam;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MatchService {

    @Autowired
    private ModelMapperConfig modelMapper;

    @Autowired
    MatchRepository matchRepository;

    Gson gson = GsonInstance.getInstance().getGson();
    public final String SPECIAL_GAME_MODE = "EVENT GAME MODE";

    /**
     * @param region
     * @param summonerName
     * @return The most recent match of the given summoner if it exists
     */
    private String loadMatchBySummonerName(String region, String summonerName, Integer count, Integer gameNumber) {
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
            List<String> matches = new MatchListBuilder().withPlatform(summoner.getPlatform()).withPuuid(summoner.getPUUID()).withCount(count).get();
            match = LOLMatch.get(opShard.get(), matches.get(gameNumber));
            if (match.getParticipants().size() == 10) {
                matchDD = findOrSaveMatch(match);
            } else
                return SPECIAL_GAME_MODE;
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
    private String getMostRecentMatchBySummonerName(String region, String summonerName) {
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
    public String loadUntilFoundMatchBySummonerName(String region, String summonerName,Integer gamesToFind) {
        //can search up to 100 per page, up to a maximum of 1000, for now only the first 100 will be able to be listed
        if(gamesToFind==null|| gamesToFind>100)
            gamesToFind=20;
        LOLMatchDD match;
        long matchId = 0;

        //we have atleast a game in the db
        String matchString = getMostRecentMatchBySummonerName(region, summonerName);
        if (!matchString.equals(gson.toJson(new NotFoundException().getMessage(), String.class))) {
            match = gson.fromJson(matchString, LOLMatchDD.class);
            matchId = match.getGameId();
        }

        List<LOLMatchDD> matchesDB = new ArrayList<>();
        LOLMatchDD matchTemp = null;
        int count = 0;
        boolean found = false;
        String exc = new NotFoundException().getMessage();
        String notFound = gson.toJson(exc, String.class);
        while (!found && count < gamesToFind) {
            matchString = loadMatchBySummonerName(region, summonerName, gamesToFind, count);
            if (!matchString.equals(gson.toJson(new NotFoundException().getMessage(), String.class))) {
                if (!matchString.equals(SPECIAL_GAME_MODE)) {
                    matchTemp = gson.fromJson(matchString, LOLMatchDD.class);
                    if (matchTemp.getGameId() == matchId)
                        found = true;
                    matchesDB.add(matchTemp);
                }
            } else if (count == 0)
                gson.toJson(new NotFoundException("games"));//return games not found
            else
                found = true;

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
        List<MatchTeamDD> teamsDD = matchDD.getTeams();
        List<MatchTeam> teams = match.getTeams();
        for (int i = 0; i < teams.size(); i++) {
            teamsDD.get(i).setWin(teams.get(i).didWin());
        }
        List<MatchParticipantDD>participants = matchDD.getParticipants();
        SummonerSpellType spellType = SummonerSpellType.AUTO_SMITE;
        for(MatchParticipantDD participantDD :participants){
            participantDD.setSummonerSpell1(spellType.getFromCode(String.valueOf(participantDD.getSummoner1Id())).get().getApiName());
            participantDD.setSummonerSpell2(spellType.getFromCode(String.valueOf(participantDD.getSummoner2Id())).get().getApiName());

        }
        if (opMatch.isEmpty())
            matchDD = matchRepository.save(matchDD);
        return matchDD;
    }
}


