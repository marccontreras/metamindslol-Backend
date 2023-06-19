package com.vas.metamindslol.Timeline;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
@Entity
public class TimelineParticipantIdentity implements Serializable
{
    private static final long serialVersionUID = -6722375304471308188L;


    @ManyToOne
    @Id
    private LOLTimeline TimeLine;

    @Id
    private int    participantId;

    private String puuid;

    public int getParticipantId()
    {
        return participantId;
    }

    public String getPuuid()
    {
        return puuid;
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
        TimelineParticipantIdentity that = (TimelineParticipantIdentity) o;
        return participantId == that.participantId && Objects.equals(puuid, that.puuid);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(participantId, puuid);
    }

    @Override
    public String toString()
    {
        return "MatchParticipantIdentity{" +
               "participantId=" + participantId +
               ", puuid='" + puuid + '\'' +
               '}';
    }
}
