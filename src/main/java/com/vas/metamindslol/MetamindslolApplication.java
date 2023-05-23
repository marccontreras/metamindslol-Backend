package com.vas.metamindslol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.league.League;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Champions;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

@SpringBootApplication
public class MetamindslolApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetamindslolApplication.class, args);
		Orianna.setRiotAPIKey("RGAPI-a5a42536-e6fc-425d-a967-e4e59e7a2012");
		Orianna.setDefaultRegion(Region.NORTH_AMERICA);

		Summoner summoner = Orianna.summonerNamed("FatalElement").get();
		System.out.println(summoner.getName() + " is level " + summoner.getLevel() + " on the " + summoner.getRegion() + " server.");

		Champions champions = Orianna.getChampions();
		Champion randomChampion = champions.get((int)(Math.random() * champions.size()));
		System.out.println("He enjoys playing champions such as " + randomChampion.getName());

		League challengerLeague = Orianna.challengerLeagueInQueue(Queue.RANKED_SOLO).get();
		Summoner bestNA = challengerLeague.get(0).getSummoner();
		System.out.println("He's not as good as " + bestNA.getName() + " at League, but probably a better Java programmer!");	}

}
