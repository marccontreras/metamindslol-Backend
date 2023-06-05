package com.vas.metamindslol;

import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
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

import static com.vas.metamindslol.R4JInstance.baseAPI;


@SpringBootApplication
public class MetamindslolApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetamindslolApplication.class, args);

        //api.getLoLAPI().getMatchAPI().getMatchList()
        for(Map.Entry<Integer, StaticChampion> champion: baseAPI.getDDragonAPI().getChampions().entrySet()) {
            System.out.println(champion.getValue().getName());
        }
        String user = "simply me";
        LeagueShard region = LeagueShard.EUW1;

        Summoner summoner = new SummonerBuilder().withPlatform(region).withName(user).get();
        Map<Integer, StaticChampion> champData = baseAPI.getDDragonAPI().getChampions();
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
