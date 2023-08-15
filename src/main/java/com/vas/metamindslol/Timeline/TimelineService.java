package com.vas.metamindslol.Timeline;

import com.vas.metamindslol.ModelMapperConfig;
import com.vas.metamindslol.exception.NotFoundException;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.types.lol.EventType;
import no.stelar7.api.r4j.impl.lol.raw.MatchV5API;
import no.stelar7.api.r4j.pojo.lol.match.v5.LOLTimeline;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.vas.metamindslol.GsonInstance.gson;

@Service
public class TimelineService {

    @Autowired
    private ModelMapperConfig modelMapper;

    @Autowired
    TimelineRepository timelineRepository;

    /**
     * @param region
     * @param gameId
     * @return The most recent timeline of the given summoner if it exists
     */
    public String loadTimelineByGameId(String region, Long gameId) {

        Optional<LeagueShard> opShard = LeagueShard.fromString(region);
        Summoner summoner;
        LOLTimeline timeline = null;
        LOLTimelineDD timelineDD = null;
        if (opShard.isPresent()) {
            String matchId= opShard.get() +"_"+ gameId;
             timeline= MatchV5API.getInstance().getTimeline(opShard.get().toRegionShard(),matchId );
             if(timeline!=null)
            timelineDD = findOrSaveTimeline(timeline);
        }
        return gson.toJson(Objects.requireNonNullElse(timelineDD, new NotFoundException().getMessage()));

    }


    /**
     * returns the timeline loaded in the db given its gameId
     *
     * @param region
     * @param gameId
     * @return The timeline mapped to timelineDB
     */
    public String getTimelineByGameId(String region, Long gameId) {
        Optional<LeagueShard> opShard = LeagueShard.fromString(region);
        LOLTimelineDD timelineDB = null;
        if (opShard.isPresent()) {
            timelineDB = timelineRepository.findByGameId(gameId);
        }
        return gson.toJson(Objects.requireNonNullElse(timelineDB, new NotFoundException().getMessage()));

    }

    /**
     * returns the timeline kills given its gameId
     *
     * @param region
     * @param gameId
     * @return The timeline mapped to timelineDB
     */
    public String getTimelineKills(String region, Long gameId) {
        Optional<LeagueShard> opShard = LeagueShard.fromString(region);
        List<TimelineFrameEventDD> eventsDB = null;
        if (opShard.isPresent()) {
            eventsDB = timelineRepository.findByGameAndEventType(gameId, EventType.CHAMPION_KILL);
        }
        return gson.toJson(Objects.requireNonNullElse(eventsDB, new NotFoundException().getMessage()));

    }


    /**
     * @param timeline
     * @return The timeline mapped to timelineDB
     * @implNote if the timeline is not on DB it's added
     */
    private LOLTimelineDD findOrSaveTimeline(LOLTimeline timeline) {
        LOLTimelineDD timelineDD;
        LOLTimelineDD optimeline = timelineRepository.findByGameId(timeline.getGameId());
        timelineDD = modelMapper.map(timeline, LOLTimelineDD.class);
        if (optimeline==null )
            timelineDD = timelineRepository.save(timelineDD);
        return timelineDD;
    }




}


