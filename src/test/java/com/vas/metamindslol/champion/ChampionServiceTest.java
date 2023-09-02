package com.vas.metamindslol.champion;

import com.vas.metamindslol.exception.NotFoundException;
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
    private final String errorMessage= new NotFoundException("champion").getMessage();
    @Test
    public void testgetChampions() {
        Assertions.assertNotEquals(errorMessage, service.getChampions());
    }

    @Test
    public void testgetChampion() {
        Assertions.assertNotEquals(errorMessage,service.getChampion(1));
    }

    @Test
    public void testgetChampionName() {
        Assertions.assertNotEquals(errorMessage,service.getChampion("Annie"));
    }
}
