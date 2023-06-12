package com.vas.metamindslol.summoner;

import com.vas.metamindslol.items.ItemService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SummonerServiceTest {
    @Autowired
    SummonerService service;

    @Test
    public void testgetSummonerByName() {

        Assertions.assertNotNull(service.getSummonerByName("EUW","simply me"));
    }

}
