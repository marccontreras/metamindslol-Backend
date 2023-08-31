package com.vas.metamindslol.champion;

import com.vas.metamindslol.ModelMapperConfig;
import com.vas.metamindslol.R4JInstance;
import com.vas.metamindslol.exception.NotFoundException;
import no.stelar7.api.r4j.impl.lol.raw.DDragonAPI;
import no.stelar7.api.r4j.pojo.lol.staticdata.champion.StaticChampion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.vas.metamindslol.GsonInstance.gson;

@Service
public class ChampionService {
    DDragonAPI dDragonAPI = R4JInstance.dDragonAPI;

    @Autowired
    private ModelMapperConfig modelMapper;

    @Autowired
    ChampionRepository championRepository;

    /**
     * @return The name and image route of the champions ordered by name
     */
    public String getChampions() {
        List<ChampionNameImage> champions = new ArrayList<>();
        List<StaticChampionDD> championsDB = championRepository.findAll();
        if (championsDB.isEmpty()) {
            championsDB = loadChampions();
        }
        championsDB.forEach((c) -> champions.add(new ChampionNameImage(c.getName(), c.getImage().getFull())));
        Collections.sort(champions);
        return gson.toJson(champions);
    }

    public List<StaticChampionDD> loadChampions() {

        Map<Integer, StaticChampion> championsDdragon = dDragonAPI.getChampions();
        List<StaticChampion> list = new ArrayList<>(championsDdragon.values());

        List<StaticChampionDD> championsList = new ModelMapperConfig().mapAsList(list, StaticChampionDD.class);
        return championRepository.saveAll(championsList);
    }

    /**
     * @param championId
     * @return The champion asked by parameter
     */
    public String getChampion(Integer championId) {
        StaticChampionDD champion = null;
        Optional<StaticChampionDD> opChampion = championRepository.findById(championId);
        if (opChampion.isEmpty()) {
            StaticChampion championD = dDragonAPI.getChampion(championId);
            champion = modelMapper.map(championD, StaticChampionDD.class);
            championRepository.save(champion);
        } else {
            champion = opChampion.get();
        }
        return gson.toJson(champion);
    }

    /**
     * @param championName
     * @return The champion asked by parameter
     */
    public String getChampion(String championName) {
        StaticChampionDD champion = championRepository.getChampionByName(championName);

        return gson.toJson(Objects.requireNonNullElse(champion, new NotFoundException().getMessage()));
    }

}
