package com.vas.metamindslol.Timeline;

import com.vas.metamindslol.matchV5.LOLMatchDD;
import no.stelar7.api.r4j.basic.constants.types.lol.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TimelineRepository extends JpaRepository<LOLTimelineDD, Long> {

    @Query
    public LOLTimelineDD findByGameId(Long gameId);

    @Query
            ("SELECT E FROM LOLTimelineDD T "
                    + " JOIN  T.frames F "
                    + " JOIN  F.events E "
                    + " WHERE E.type=:eventType AND T.gameId=:gameId ")
    public List<TimelineFrameEventDD> findByGameAndEventType(Long gameId, EventType eventType);
}
