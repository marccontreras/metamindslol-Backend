package com.vas.metamindslol.Timeline;

import jakarta.persistence.*;
import no.stelar7.api.r4j.pojo.lol.match.v5.TimelineFrameEvent;
import no.stelar7.api.r4j.pojo.lol.match.v5.TimelineParticipantFrame;

import java.io.Serializable;
import java.util.*;
@Entity
public class TimelineFrame implements Serializable
{
    private static final long serialVersionUID = -7057434060412917705L;
    @ElementCollection
    private List<TimelineFrameEvent>              events;
    @ElementCollection
    private Map<String, TimelineParticipantFrame> participantFrames;

    @ManyToOne
    @Id
    private LOLTimeline TimeLine;

    @Id
    private long                                  timestamp;
    
    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }

    public List<TimelineFrameEvent> getEvents()
    {
        return events;
    }

    public Map<String, TimelineParticipantFrame> getParticipantFrames()
    {
        return participantFrames;
    }
    
    public long getTimestamp()
    {
        return timestamp;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        TimelineFrame that = (TimelineFrame) o;
        return timestamp == that.timestamp && Objects.equals(events, that.events) && Objects.equals(participantFrames, that.participantFrames);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(events, participantFrames, timestamp);
    }
    
    @Override
    public String toString()
    {
        return "TimelineFrame{" +
               "events=" + events +
               ", participantFrames=" + participantFrames +
               ", timestamp=" + timestamp +
               '}';
    }
}
