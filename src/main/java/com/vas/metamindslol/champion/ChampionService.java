package com.vas.metamindslol.champion;

import com.vas.metamindslol.ModelMapperConfig;
import com.vas.metamindslol.R4JInstance;
import no.stelar7.api.r4j.impl.lol.raw.DDragonAPI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.vas.metamindslol.GsonInstance.gson;

@Service
public class ChampionService {
    DDragonAPI dDragonAPI= R4JInstance.dDragonAPI;

    @Autowired
    private ModelMapperConfig modelMapper;

    @Autowired
    ChampionRepository championRepository;
    /**
     *
     * @return The name and image route of the champions ordered by name
     */
    public String getChampions() {
        List<ChampionNameImage> champions= new ArrayList<>();
        List<StaticChampion>championsDB= championRepository.findAll();
        if(championsDB.isEmpty()) {
            championsDB= loadChampions();
        }
        championsDB.forEach((c) -> champions.add(new ChampionNameImage(c.getName(), c.getImage().getFull())));
        Collections.sort( champions);
        return gson.toJson(champions);
    }

    public List<StaticChampion> loadChampions() {

        Map<Integer,no.stelar7.api.r4j.pojo.lol.staticdata.champion.StaticChampion > championsDdragon=dDragonAPI.getChampions();
        List<no.stelar7.api.r4j.pojo.lol.staticdata.champion.StaticChampion> list = new ArrayList<no.stelar7.api.r4j.pojo.lol.staticdata.champion.StaticChampion>(championsDdragon.values());

        List<StaticChampion> championsList= new ModelMapperConfig().mapAsList(list,StaticChampion.class);
        championRepository.saveAll(championsList);
        return championRepository.saveAll(championsList);
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
