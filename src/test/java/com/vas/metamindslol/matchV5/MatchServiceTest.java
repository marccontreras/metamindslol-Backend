package com.vas.metamindslol.matchV5;

import com.vas.metamindslol.R4JInstance;
import com.vas.metamindslol.exception.NotFoundException;
import jakarta.transaction.Transactional;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Transactional
@SpringBootTest
public class MatchServiceTest {

    @Autowired
    MatchService service;

    private final String name = "simply me";
    private final String errorMessage= new NotFoundException("Matches").getMessage();
    private final String region = "EUW";

    @Test
    public void testgetMatchesBySummonerName() {
        Assertions.assertNotEquals(errorMessage,service.getMatchesBySummonerName(region, name));
    }
        @Test
    public void testloadUntilFoundMatchBySummonerName() {
            Assertions.assertNotEquals(errorMessage, service.loadUntilFoundMatchBySummonerName(region, name,null));
    }
}
