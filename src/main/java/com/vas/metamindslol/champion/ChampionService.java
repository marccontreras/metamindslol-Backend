package com.vas.metamindslol.champion;

import com.vas.metamindslol.R4JInstance;
import no.stelar7.api.r4j.impl.lol.raw.DDragonAPI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.vas.metamindslol.GsonInstance.gson;

@Service
public class ChampionService {
    DDragonAPI dDragonAPI= R4JInstance.dDragonAPI;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ChampionRepository championRepository;
    /**
     *
     * @return The name and image route of the champions ordered by name
     */
    public String getChampions() {
        List<ChampionNameImage> champions= new ArrayList<>();

        dDragonAPI.getChampions().forEach((k,v)-> champions.add(new ChampionNameImage(v.getName(),v.getImage().getFull())));
        Collections.sort( champions);
        return gson.toJson(champions);
    }

    /**
     *
     * @param championId
     * @return The champion asked by parameter
     */
    public String getChampion(Integer championId) {
        StaticChampion champion= null;
        Optional<StaticChampion> opChampion= championRepository.findById(championId);
        if(opChampion.isEmpty()){
            no.stelar7.api.r4j.pojo.lol.staticdata.champion.StaticChampion championD= dDragonAPI.getChampion(championId);
            champion=modelMapper.map(championD,StaticChampion.class);
            championRepository.save(champion);
        }else{
            champion=opChampion.get();
        }
         return gson.toJson(champion);
    }

    //TODO: when there's the search-bar, be sure to pass the name and image of the champ(maybe even wr)
}
