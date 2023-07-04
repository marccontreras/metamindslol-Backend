package com.vas.metamindslol.Timeline;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

import lombok.Data;
import no.stelar7.api.r4j.pojo.lol.match.v5.TimelineParticipantFrame;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class TimelineFrameDD implements Serializable {
    private static final long serialVersionUID = -7057434060412917705L;

    @Id
    @GeneratedValue(generator = "timelineFrame-sequence-generator")
    @GenericGenerator(
            name = "timelineFrame-sequence-generator")
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<TimelineFrameEventDD> events;

    //for the moment, there's no need to have the participant frame values(gold,stats...), in case it's needed to change
    // it, remove the transient property and add a model for TimelinePosition
    private transient Map<String, TimelineParticipantFrame> participantFrames;

    private long timestamp;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<TimelineFrameEventDD> getEvents() {
        return events;
    }

    public Map<String, TimelineParticipantFrame> getParticipantFrames() {
        return participantFrames;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimelineFrameDD that = (TimelineFrameDD) o;
        return timestamp == that.timestamp && Objects.equals(events, that.events) && Objects.equals(participantFrames, that.participantFrames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(events, participantFrames, timestamp);
    }

    @Override
    public String toString() {
        return "TimelineFrame{" +
                "events=" + events +
                ", participantFrames=" + participantFrames +
                ", timestamp=" + timestamp +
                '}';
    }
}
