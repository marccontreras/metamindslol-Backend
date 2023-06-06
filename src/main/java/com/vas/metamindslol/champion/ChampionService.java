package com.vas.metamindslol.champion;

import no.stelar7.api.r4j.pojo.lol.staticdata.champion.StaticChampion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.vas.metamindslol.R4JInstance.dDragonAPI;

@Service
public class ChampionService {

    /**
     *
     * @return The name and image route of the champions ordered by name
     */
    public List<ChampionNameImage> getChampions() {
        List<ChampionNameImage> champions= new ArrayList<>();

        dDragonAPI.getChampions().forEach((k,v)-> champions.add(new ChampionNameImage(v.getName(),v.getImage().getFull())));
        Collections.sort( champions);
        return champions;
    }

    /**
     *
     * @param championId
     * @return The champion asked by parameter
     */
    public  StaticChampion getChampion(Integer championId) {

         return dDragonAPI.getChampion(championId);
    }

    //TODO: when there's the search-bar, be sure to pass the name and image of the champ(maybe even wr)
}
