package com.vas.metamindslol.summoner;

import com.google.gson.Gson;
import com.vas.metamindslol.GsonInstance;
import com.vas.metamindslol.ModelMapperConfig;
import com.vas.metamindslol.exception.NotFoundException;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.vas.metamindslol.R4JInstance.baseAPI;

@Service
public class SummonerService {

    @Autowired
    private ModelMapperConfig modelMapper;

    @Autowired
    SummonerRepository summonerRepository;

    Gson gson = GsonInstance.getInstance().getGson();
    /**
     * @param region
     * @param summonerName
     * @return The summoner of the region
     */
    public String getSummonerByNameAndRegion(String region, String summonerName) {
        Optional<LeagueShard> opShard = LeagueShard.fromString(region);
        SummonerDD summonerDD = null;
        Summoner summoner = null;
        if (opShard.isPresent()) {
            summonerDD = summonerRepository.findByNameAndRegion(summonerName, opShard.get());
            if (summonerDD == null) {
                summoner = baseAPI.getLoLAPI().getSummonerAPI().getSummonerByName(opShard.get(), summonerName);
                if(summoner !=null)
                summonerDD = loadSummoner(summoner);
            }
        }
        return gson.toJson(Objects.requireNonNullElse(summonerDD, new NotFoundException("Region").getMessage()));


    }

    /**
     * @param summonerName
     * @return The summoner of the region
     */
    public String searchSummonerByName(String summonerName) {

        List<SummonerDD> summonerDD = null;
        summonerDD = summonerRepository.findSummonerByName(summonerName);
        return gson.toJson(Objects.requireNonNullElse(summonerDD, new NotFoundException("summoners that starts with "+ summonerName).getMessage()));
    }

    private SummonerDD loadSummoner(Summoner summoner) {
        SummonerDD summonerDD = modelMapper.map(summoner, SummonerDD.class);
        return summonerRepository.save(summonerDD);
    }
}


