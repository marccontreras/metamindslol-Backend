package com.vas.metamindslol.Timeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimelineController {
    @Autowired
    TimelineService service;



    @GetMapping(value = "/timeline/{region}/{gameId}")
    public String getSummonerByName(@PathVariable String region,@PathVariable Long gameId){
        return service.loadTimelineByGameId(region,gameId);
    }
}
