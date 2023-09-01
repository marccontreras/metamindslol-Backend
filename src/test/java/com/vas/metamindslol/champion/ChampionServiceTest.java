package com.vas.metamindslol.champion;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class ChampionServiceTest {
    @Autowired
    ChampionService service;
    @Test
    public void testgetChampions() {
        Assertions.assertNotNull(service.getChampions());
    }

    @Test
    public void testgetChampion() {
        Assertions.assertNotNull(service.getChampion(1));
    }
}
