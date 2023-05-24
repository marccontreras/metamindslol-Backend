package com.vas.metamindslol;

import no.stelar7.api.r4j.basic.APICredentials;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.lol.builders.summoner.SummonerBuilder;
import no.stelar7.api.r4j.impl.lol.raw.ImageAPI;
import no.stelar7.api.r4j.pojo.lol.match.v5.LOLMatch;
import no.stelar7.api.r4j.pojo.lol.match.v5.MatchParticipant;
import no.stelar7.api.r4j.pojo.lol.match.v5.MatchPerks;
import no.stelar7.api.r4j.pojo.lol.staticdata.champion.StaticChampion;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;


@SpringBootApplication
public class MetamindslolApplication {

    private static final String apiKey = "RGAPI-9f23e9dd-c474-4cbc-854c-7ef1ad4c2acc";

    public static void main(String[] args) {
        SpringApplication.run(MetamindslolApplication.class, args);
        APICredentials creds = new APICredentials(apiKey);
        R4J api = new R4J(creds);
        String user = "simply me";
        LeagueShard region = LeagueShard.EUW1;

        Summoner summoner = new SummonerBuilder().withPlatform(region).withName(user).get();
        Map<Integer, StaticChampion> champData = api.getDDragonAPI().getChampions();
        //pfp
        String pfp = ImageAPI.getInstance().getProfileIcon(region, user);
        //name and lv
        int level = summoner.getSummonerLevel();
        String name = summoner.getName();
        //most recent game
        List<String> matches = summoner.getLeagueGames().get();
        LOLMatch match = LOLMatch.get(region, matches.get(0));
        MatchParticipant self = match.getParticipants().stream().filter(p -> p.getPuuid().equals(summoner.getPUUID())).findFirst().get();
        StaticChampion champion = champData.get(self.getChampionId());
        MatchPerks summs = self.getPerks();
        boolean won = self.didWin();

        System.out.println("Profile icon: " + pfp);
        System.out.println(name + ", Level " + level);
        System.out.println();
        System.out.format(name + " %s their most recent game.", won ? "won" : "lost");
        System.out.println();
        System.out.println("They were playing " + self.getChampionSelectLane() + " " + champion.getName());
    }
}
