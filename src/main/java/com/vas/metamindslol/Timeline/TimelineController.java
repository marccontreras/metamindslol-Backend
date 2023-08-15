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
    public String getTimelineByGameId(@PathVariable String region,@PathVariable Long gameId){
        return service.getTimelineByGameId(region,gameId);
    }
    @GetMapping(value = "/timeline/{region}/{gameId}/kills")
    public String getTimelineKills(@PathVariable String region,@PathVariable Long gameId){
        return service.getTimelineKills(region,gameId);
    }
}
