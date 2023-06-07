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

    @GetMapping(value = "/champions")
    public  String getChampions(){
        return service.getChampions();

    }

    @GetMapping(value = "/champions/{championId}")
    public String getChampion(@PathVariable Integer championId){
        return service.getChampion(championId);
    }

    //default test crida
    @GetMapping(value = "/")
    public String getIt(){
        return "IT";
    }

}
