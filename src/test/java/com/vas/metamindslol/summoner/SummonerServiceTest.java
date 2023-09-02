package com.vas.metamindslol.summoner;

import com.vas.metamindslol.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class SummonerServiceTest {
    @Autowired
    SummonerService service;

    private final String summonerName = "simply me";
    private final String errorMessage= new NotFoundException("Summoner").getMessage();
    private final String errorMessage2= new NotFoundException("summoners that starts with "+ summonerName).getMessage();
    @Test
    public void testgetSummonerByName() {
        String region = "EUW";
        Assertions.assertNotEquals(errorMessage,service.getSummonerByNameAndRegion(region,summonerName));
    }
    @Test
    public void testsearchSummonerByName() {

        Assertions.assertNotEquals(errorMessage2,service.searchSummonerByName(summonerName));
    }
}
