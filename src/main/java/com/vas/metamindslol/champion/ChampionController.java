package com.vas.metamindslol.champion;

import com.google.gson.Gson;
import no.stelar7.api.r4j.pojo.lol.staticdata.champion.StaticChampion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChampionController {
    @Autowired
    ChampionService service;
    private static Gson gson = new Gson();
    @GetMapping(value = "/champions")
    public  String getChampions(){
        return gson.toJson(service.getChampions(),ChampionNameImage.class);

    }

    @GetMapping(value = "/champions/{championId}")
    public String getChampion(@PathVariable Integer championId){
        return gson.toJson(service.getChampion(championId));
    }

    //default test crida
    @GetMapping(value = "/")
    public String getIt(){
        return "IT";
    }

}
