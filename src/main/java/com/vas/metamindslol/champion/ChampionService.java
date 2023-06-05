package com.vas.metamindslol.champion;

import no.stelar7.api.r4j.basic.utils.Pair;
import no.stelar7.api.r4j.pojo.lol.staticdata.shared.Image;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.vas.metamindslol.R4JInstance.dDragonAPI;

@Service
public class ChampionService {


    public static List<ChampionNameImage> getChampions() {
        //TODO: crear classe que tingui les 2 strings, i ordenar
        List<ChampionNameImage> champions= new ArrayList<>();

        dDragonAPI.getChampions().forEach((k,v)-> champions.add(new ChampionNameImage(v.getName(),v.getImage().getFull())));
        return champions;
    }
}
