package com.vas.metamindslol.champion;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ChampionServiceTest {
    @Autowired
    ChampionService service;
    @Test
    public void testgetChampions() {
        Assert.assertNotNull( service.getChampions());
    }

    @Test
    public void testgetChampion() {
        Assert.assertNotNull( service.getChampion(1));
    }
}
