package com.vas.metamindslol.matchV5;

import com.google.gson.Gson;
import com.vas.metamindslol.GsonInstance;
import com.vas.metamindslol.ModelMapperConfig;
import com.vas.metamindslol.R4JInstance;
import com.vas.metamindslol.exception.NotFoundException;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.lol.GameQueueType;
import no.stelar7.api.r4j.basic.constants.types.lol.MatchlistMatchType;
import no.stelar7.api.r4j.basic.constants.types.lol.SummonerSpellType;
import no.stelar7.api.r4j.impl.lol.builders.matchv5.match.MatchListBuilder;
import no.stelar7.api.r4j.pojo.lol.match.v5.LOLMatch;
import no.stelar7.api.r4j.pojo.lol.match.v5.MatchTeam;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.pojo.shared.RiotAccount;
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
    public final String SPECIAL_GAME_MODE = "NON-RANKED GAMES";

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

            summoner = getSummonerNameAndPart(summonerName, opShard.get());

            //add the start to be able to get games after the 100 first
            List<String> matches = new MatchListBuilder().withPlatform(summoner.getPlatform()).withPuuid(summoner.getPUUID()).withType(MatchlistMatchType.RANKED).withCount(count).get();
            match = LOLMatch.get(opShard.get(), matches.get(gameNumber));
            if (match.getQueue().equals(GameQueueType.RANKED_SOLO_5X5) || match.getQueue().equals(GameQueueType.RANKED_FLEX_SR) || match.getQueue().equals(GameQueueType.TEAM_BUILDER_RANKED_SOLO) || match.getQueue().equals(GameQueueType.TEAM_BUILDER_DRAFT_RANKED_5X5)) {
                matchDD = findOrSaveMatch(match);
            } else return SPECIAL_GAME_MODE;
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

            String[] summonerParts = summonerName.split("#");
            String tag;
            if (summonerParts.length == 1) tag = opShard.get().toString().replaceAll("[0-9]", "");
            else {
                tag = summonerParts[1];

                summonerName = summonerParts[0];
            }

            matchesDB = matchRepository.getMatchesByRiotIdNameAndTagLine(summonerName, tag);
        }
        return gson.toJson(Objects.requireNonNullElse(matchesDB, new NotFoundException("Matches").getMessage()));

    }

    /**
     * returns the most recent match loaded in the db given its summoner name
     *
     * @param region
     * @param summonerName
     * @return The match mapped to matchDB
     */
    private String getMostRecentMatchBySummonerNameAndTag(String region, String summonerName) {
        Optional<LeagueShard> opShard = LeagueShard.fromString(region);
        LOLMatchDD matchesDB = null;
        if (opShard.isPresent()) {
            //make a method that finds by matchParticipant, summonerName(maybe puuid)
            String[] summonerParts = summonerName.split("#");
            String tag;
            if (summonerParts.length == 1) tag = opShard.get().toString().replaceAll("[0-9]", "");
            else {
                tag = summonerParts[1];
                summonerName = summonerParts[0];
            }

            matchesDB = matchRepository.getMostRecentMatchByRiotIdNameAndTagLine(summonerName, tag);
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
    public String loadUntilFoundMatchBySummonerName(String region, String summonerName, Integer gamesToFind) {
        //can search up to 100 per page, up to a maximum of 1000, in this method only the first 100 will be able
        // to be listed
        if (gamesToFind == null || gamesToFind > 100) gamesToFind = 20;
        LOLMatchDD match;
        long matchId = 0;

        //we have atleast a game in the db
        String matchString = getMostRecentMatchBySummonerNameAndTag(region, summonerName);
        if (!matchString.equals(gson.toJson(new NotFoundException().getMessage(), String.class))) {
            match = gson.fromJson(matchString, LOLMatchDD.class);
            matchId = match.getGameId();
        }

        List<LOLMatchDD> matchesDB = new ArrayList<>();
        LOLMatchDD matchTemp = null;
        int count = 0;
        int gamesFound = 0;
        boolean found = false;
        while (!found && gamesFound < gamesToFind && count < 100) {
            matchString = loadMatchBySummonerName(region, summonerName, gamesToFind, count);
            if (!matchString.equals(gson.toJson(new NotFoundException().getMessage(), String.class))) {
                matchTemp = gson.fromJson(matchString, LOLMatchDD.class);
                if (matchTemp.getGameId() == matchId) found = true;
                matchesDB.add(matchTemp);
                gamesFound++;
            } else if (count == 0) return gson.toJson(new NotFoundException("Matches"));//summoner has no games
            else found = true;

            count++;
        }
        return gson.toJson(matchesDB);
    }

    /**
     * @param summonerName
     * @param region
     * @return The summoner with the summonerName in the region
     */
    public Summoner getSummonerNameAndPart(String summonerName,LeagueShard region) {
        Summoner summoner;

        String[] summonerParts = summonerName.split("#");
        String tag;
        if (summonerParts.length == 1) tag = region.toString().replaceAll("[0-9]", "");
        else {
            tag = summonerParts[1];

            summonerName = summonerParts[0];
        }

        RiotAccount account = R4JInstance.baseAPI.getAccountAPI().getAccountByTag(LeagueShard.EUW1.toRegionShard(), summonerName, tag);
        summoner = Summoner.byPUUID(LeagueShard.EUW1, account.getPUUID());
        return summoner;
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
        List<MatchParticipantDD> participants = matchDD.getParticipants();
        SummonerSpellType spellType = SummonerSpellType.AUTO_SMITE;
        for (MatchParticipantDD participantDD : participants) {
            participantDD.setSummonerSpell1(spellType.getFromCode(String.valueOf(participantDD.getSummoner1Id())).get().getApiName());
            participantDD.setSummonerSpell2(spellType.getFromCode(String.valueOf(participantDD.getSummoner2Id())).get().getApiName());

        }
        if (opMatch.isEmpty()) matchDD = matchRepository.save(matchDD);
        return matchDD;
    }
}


