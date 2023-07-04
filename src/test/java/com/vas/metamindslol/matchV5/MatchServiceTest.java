package com.vas.metamindslol.matchV5;

import com.vas.metamindslol.R4JInstance;
import com.vas.metamindslol.exception.NotFoundException;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MatchServiceTest {


    @Autowired
    MatchService service;

    private String name = "simply me";
    private Summoner summoner = R4JInstance.loLAPI.getSummonerAPI().getSummonerByName(LeagueShard.EUW1,name);
    private String region = "EUW";

    @Test
    public void testgetMatch() {

        Assertions.assertNotNull(service.loadMatchBySummoner(region, summoner, 1));
    }

    @Test
    public void testgetMostRecentMatch() {
        Assert.assertNotEquals(new NotFoundException().getMessage(), service.loadMostRecentMatchBySummoner(region, summoner));
    }

    @Test
    public void testgetMostRecentMatchByName() {
        Assert.assertNotEquals(new NotFoundException().getMessage(), service.loadMatchBySummonerName(region, name,null));
    }
}
