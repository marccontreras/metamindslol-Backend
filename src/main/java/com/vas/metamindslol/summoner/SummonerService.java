package com.vas.metamindslol.summoner;

import com.google.gson.Gson;
import com.vas.metamindslol.GsonInstance;
import com.vas.metamindslol.ModelMapperConfig;
import com.vas.metamindslol.R4JInstance;
import com.vas.metamindslol.exception.NotFoundException;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import no.stelar7.api.r4j.pojo.shared.RiotAccount;
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

        if (opShard.isPresent()) {
            summonerDD = summonerRepository.findByNameAndRegion(summonerName, opShard.get());
            if (summonerDD == null) {
                summonerDD = getSummonerNameAndPart(summonerName, opShard);
                if (summonerDD.getName()!=null)
                    summonerDD = loadSummoner(summonerDD);
                else
                    return gson.toJson(new NotFoundException("Summoner").getMessage());
            }
        }
        return gson.toJson(Objects.requireNonNullElse(summonerDD, new NotFoundException("Region").getMessage()));
    }


    /**
     * @param summonerName
     * @param opShard      region
     * @return The summonerDD with the summonerName in the region
     */
    private SummonerDD getSummonerNameAndPart(String summonerName, Optional<LeagueShard> opShard) {
        Summoner summoner;
        String[] summonerParts = summonerName.split("#");
        String tag;
        if (summonerParts.length == 1)
            tag = opShard.get().toString().replaceAll("[0-9]", "");
        else {
            tag = summonerParts[1];

            summonerName = summonerParts[0];
        }

        RiotAccount account = R4JInstance.baseAPI.getAccountAPI().getAccountByTag(LeagueShard.EUW1.toRegionShard(), summonerName, tag);
        summoner = Summoner.byPUUID(LeagueShard.EUW1, account.getPUUID());
        SummonerDD summonerDD = modelMapper.map(summoner, SummonerDD.class);
        String summonerNameAndTag = account.getName() + "#" + account.getTag();
        summonerDD.setName(summonerNameAndTag);
        return summonerDD;
    }


    /**
     * @param summonerName
     * @return The summoner of the region
     */
    public String searchSummonerByName(String summonerName) {

        List<SummonerDD> summonerDD = null;
        summonerDD = summonerRepository.findSummonerByName(summonerName);
        return gson.toJson(Objects.requireNonNullElse(summonerDD, new NotFoundException("summoners that starts with " + summonerName).getMessage()));
    }

    private SummonerDD loadSummoner(SummonerDD summonerDD) {
        //SummonerDD summonerDD = modelMapper.map(summoner, SummonerDD.class);
        return summonerRepository.save(summonerDD);
    }
}


