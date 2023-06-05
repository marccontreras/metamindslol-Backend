package com.vas.metamindslol.champion;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChampionController {
    @GetMapping(value = "/champions")
    public String getChampions(){
        return new Gson().toJson(ChampionService.getChampions());

    }

    //default test crida
    @GetMapping(value = "/")
    public String getIt(){
        return "IT";
    }

}
