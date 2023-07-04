package com.vas.metamindslol.Timeline;

import com.vas.metamindslol.matchV5.LOLMatchDD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TimelineRepository extends JpaRepository<LOLTimelineDD, Long> {

@Query
    public LOLTimelineDD findByGameId(Long gameId);
}
