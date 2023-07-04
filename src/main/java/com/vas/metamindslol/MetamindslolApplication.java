package com.vas.metamindslol;

import com.vas.metamindslol.champion.ChampionService;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.api.regions.RegionShard;
import no.stelar7.api.r4j.basic.constants.types.lol.GameQueueType;
import no.stelar7.api.r4j.basic.constants.types.lol.TierDivisionType;
import no.stelar7.api.r4j.basic.utils.LazyList;
import no.stelar7.api.r4j.impl.lol.builders.summoner.SummonerBuilder;
import no.stelar7.api.r4j.impl.lol.raw.ImageAPI;
import no.stelar7.api.r4j.impl.lol.raw.MatchV5API;
import no.stelar7.api.r4j.pojo.lol.league.LeagueEntry;
import no.stelar7.api.r4j.pojo.lol.match.v5.LOLMatch;
import no.stelar7.api.r4j.pojo.lol.match.v5.LOLTimeline;
import no.stelar7.api.r4j.pojo.lol.match.v5.MatchParticipant;
import no.stelar7.api.r4j.pojo.lol.match.v5.MatchPerks;
import no.stelar7.api.r4j.pojo.lol.staticdata.champion.StaticChampion;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class MetamindslolApplication {


    public static void main(String[] args) {
        ApplicationContext context =SpringApplication.run(MetamindslolApplication.class, args);



        ApiPropertiesTestComponent testComponent = context.getBean(ApiPropertiesTestComponent.class);
        testComponent.printApiKey();
        //R4JInstance.apiKey=testComponent.getApiKey();
        //R4JInstance.setApiKey(testComponent.getApiKey());

        //api.getLoLAPI().getMatchAPI().getMatchList()
        for(Map.Entry<Integer, StaticChampion> champion: R4JInstance.baseAPI.getDDragonAPI().getChampions().entrySet()) {
            System.out.println(champion.getValue().getName());
        }
        String user = "simply me";
        LeagueShard region = LeagueShard.EUW1;

        //Summoner summoner = new SummonerBuilder().withPlatform(region).withName(user).get();
        Summoner summoner = R4JInstance.baseAPI.getLoLAPI().getSummonerAPI().getSummonerByName(region,user);

        Map<Integer, StaticChampion> champData = R4JInstance.baseAPI.getDDragonAPI().getChampions();
        //pfp
        String pfp = ImageAPI.getInstance().getProfileIcon(region, user);
        //name and lv
        int level = summoner.getSummonerLevel();
        String name = summoner.getName();
        //most recent game
        List<String> matches = summoner.getLeagueGames().get();
        LOLMatch match = LOLMatch.get(region, matches.get(0));
        String matchId= matches.get(0);
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


        //testing things
        //timeline
        LOLTimeline timeline=MatchV5API.getInstance().getTimeline(RegionShard.EUROPE,matches.get(0));
        // get all games

        LazyList<LeagueEntry> games= R4JInstance.baseAPI.getLoLAPI().getLeagueAPI().getLeagueByTierDivisionLazy(LeagueShard.EUW1, GameQueueType.RANKED_SOLO_5X5, TierDivisionType.CHALLENGER_I);
        games.loadFully();
        games.forEach(t-> System.out.println(t.toString()));
    }
}
