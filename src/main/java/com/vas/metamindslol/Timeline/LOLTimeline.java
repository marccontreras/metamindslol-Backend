package com.vas.metamindslol.Timeline;

import jakarta.persistence.*;
import lombok.Data;


import java.io.Serializable;
import java.util.*;

@Entity
@Data
public class LOLTimeline implements Serializable
{
    private static final long serialVersionUID = -4143523364776512003L;
    
    private int                               frameInterval;
    @OneToMany
    private List<TimelineFrame>               frames;
    @Id
    private long                              gameId;
    @OneToMany
    private List<TimelineParticipantIdentity> participants;
    
    public int getFrameInterval()
    {
        return frameInterval;
    }

    public List<TimelineFrame> getFrames()
    {
        return frames;
    }
    
    public long getGameId()
    {
        return gameId;
    }


    public List<TimelineParticipantIdentity> getParticipants()
    {
        return participants;
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
        LOLTimeline that = (LOLTimeline) o;
        return frameInterval == that.frameInterval && gameId == that.gameId && Objects.equals(frames, that.frames) && Objects.equals(participants, that.participants);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(frameInterval, frames, gameId, participants);
    }
    
    @Override
    public String toString()
    {
        return "LOLTimeline{" +
               "frameInterval=" + frameInterval +
               ", frames=" + frames +
               ", gameId=" + gameId +
               ", participants=" + participants +
               '}';
    }
}
