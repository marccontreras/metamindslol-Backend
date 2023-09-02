package com.vas.metamindslol.champion;

import com.google.gson.Gson;
import com.vas.metamindslol.GsonInstance;
import com.vas.metamindslol.ModelMapperConfig;
import com.vas.metamindslol.R4JInstance;
import com.vas.metamindslol.exception.NotFoundException;
import no.stelar7.api.r4j.impl.lol.raw.DDragonAPI;
import no.stelar7.api.r4j.pojo.lol.staticdata.champion.StaticChampion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChampionService {
    DDragonAPI dDragonAPI = R4JInstance.dDragonAPI;

    @Autowired
    private ModelMapperConfig modelMapper;

    @Autowired
    ChampionRepository championRepository;

    Gson gson = GsonInstance.getInstance().getGson();
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
        for(StaticChampionDD champion:championsList){
            champion.getPassive().setDescription(sanitize(champion.getPassive().getDescription()));
            champion.getSpells().forEach(t->t.setDescription(sanitize(t.getDescription())));
        }

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
            champion.getPassive().setDescription(sanitize(champion.getPassive().getDescription()));
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

        return gson.toJson(Objects.requireNonNullElse(champion, new NotFoundException("champion").getMessage()));
    }

    private String sanitize(String inData)
    {
        String outData = inData;
        outData = outData.replaceAll("<br>", "\n");
        outData = outData.replaceAll("<.+?>", "");
        outData = outData.replaceAll("\n", "<br>");
        return outData;
    }

}
