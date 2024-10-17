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
import no.stelar7.api.r4j.pojo.shared.RiotAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
public class MetamindslolApplication {


    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MetamindslolApplication.class, args);


        ApiPropertiesTestComponent testComponent = context.getBean(ApiPropertiesTestComponent.class);
        testComponent.printApiKey();
        //R4JInstance.apiKey=testComponent.getApiKey();
        //R4JInstance.setApiKey(testComponent.getApiKey());

        //api.getLoLAPI().getMatchAPI().getMatchList()
        for (Map.Entry<Integer, StaticChampion> champion : R4JInstance.baseAPI.getDDragonAPI().getChampions().entrySet()) {
            System.out.println(champion.getValue().getName());
        }
        String user = "simply me#EUW";
        String summonerName = user;

        Optional<LeagueShard> opShard = LeagueShard.fromString("EUW");
        LeagueShard region = LeagueShard.EUW1;
        String[] summonerParts = summonerName.split("#");
        String tag;
        if (summonerParts.length == 1)
            tag = opShard.get().toString().replaceAll("[0-9]", "");
        else {
            tag = summonerParts[1];

            summonerName = summonerParts[0];
        }

        RiotAccount account = R4JInstance.baseAPI.getAccountAPI().getAccountByTag(LeagueShard.EUW1.toRegionShard(), summonerName, tag);
        //retorna null el name, mirar pq
        String summonerNameAndTag = account.getName() + "#" + account.getTag();
        Summoner summoner = Summoner.byPUUID(LeagueShard.EUW1, account.getPUUID());


        Map<Integer, StaticChampion> champData = R4JInstance.baseAPI.getDDragonAPI().getChampions();
        //pfp
        String pfp = ImageAPI.getInstance().getProfileIcon(region, user);
        //name and lv
        int level = summoner.getSummonerLevel();
        String name = summonerNameAndTag;
        //most recent game
        List<String> matches = summoner.getLeagueGames().get();
        LOLMatch match = LOLMatch.get(region, matches.get(0));
        //String matchId= matches.get(0);
        MatchParticipant self = match.getParticipants().stream().filter(p -> p.getPuuid().equals(summoner.getPUUID())).findFirst().get();
        StaticChampion champion = champData.get(self.getChampionId());
        //MatchPerks summs = self.getPerks();
        boolean won = self.didWin();
        System.out.println("Profile icon: " + pfp);
        System.out.println(name + ", Level " + level);
        System.out.println();
        System.out.format(name + " %s their most recent game.", won ? "won" : "lost");
        System.out.println();
        System.out.println("They were playing " + self.getChampionSelectLane() + " " + champion.getName());


        //testing things
        //timeline
        LOLTimeline timeline = MatchV5API.getInstance().getTimeline(RegionShard.EUROPE, matches.get(0));
        // get all games

        LazyList<LeagueEntry> games = R4JInstance.baseAPI.getLoLAPI().getLeagueAPI().getLeagueByTierDivisionLazy(LeagueShard.EUW1, GameQueueType.RANKED_SOLO_5X5, TierDivisionType.CHALLENGER_I);
        games.loadFully();
        games.forEach(t -> System.out.println(t.toString()));
    }
}
