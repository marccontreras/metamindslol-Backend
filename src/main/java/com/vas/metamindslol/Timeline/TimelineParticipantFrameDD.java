package com.vas.metamindslol.Timeline;

import jakarta.persistence.*;
import lombok.Data;
import no.stelar7.api.r4j.pojo.lol.match.v5.ChampionStats;
import no.stelar7.api.r4j.pojo.lol.match.v5.DamageStats;
import no.stelar7.api.r4j.pojo.lol.match.v5.TimelinePosition;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
public class TimelineParticipantFrameDD implements Serializable {

    @Id
    @GeneratedValue(generator = "timelineParticipantFrame-sequence-generator")
    @GenericGenerator(
            name = "timelineParticipantFrame-sequence-generator")
    private long id;
    //@ManyToOne
    //private TimelineFrame timelineFrame;
    private static final long serialVersionUID = 8518386569606056255L;

    //for the moment, there's no need to have the exact champ values at the exact moment, in case it's needed to change
    // it, remove the transient property and add a model for TimelinePosition
    private transient ChampionStats championStats;
    private int currentGold;
    private transient DamageStats damageStats;
    private int goldPerSecond;
    private int jungleMinionsKilled;
    private int level;
    private int minionsKilled;

    private int participantId;
    //for the moment, there's no need to have the exact position, in case it's needed to change it,
    // remove the transient property and add a model for TimelinePosition
    private transient TimelinePosition position;
    private int timeEnemySpentControlled;
    private int totalGold;
    private int xp;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public ChampionStats getChampionStats() {
        return championStats;
    }

    public int getCurrentGold() {
        return currentGold;
    }

    public DamageStats getDamageStats() {
        return damageStats;
    }

    public int getGoldPerSecond() {
        return goldPerSecond;
    }

    public int getJungleMinionsKilled() {
        return jungleMinionsKilled;
    }

    public int getLevel() {
        return level;
    }

    public int getMinionsKilled() {
        return minionsKilled;
    }

    public int getParticipantId() {
        return participantId;
    }

    public TimelinePosition getPosition() {
        return position;
    }

    public int getTimeEnemySpentControlled() {
        return timeEnemySpentControlled;
    }

    public int getTotalGold() {
        return totalGold;
    }

    public int getXp() {
        return xp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimelineParticipantFrameDD that = (TimelineParticipantFrameDD) o;
        return currentGold == that.currentGold && goldPerSecond == that.goldPerSecond && jungleMinionsKilled == that.jungleMinionsKilled && level == that.level && minionsKilled == that.minionsKilled && participantId == that.participantId && timeEnemySpentControlled == that.timeEnemySpentControlled && totalGold == that.totalGold && xp == that.xp && Objects.equals(championStats, that.championStats) && Objects.equals(damageStats, that.damageStats) && Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(championStats, currentGold, damageStats, goldPerSecond, jungleMinionsKilled, level, minionsKilled, participantId, position, timeEnemySpentControlled, totalGold, xp);
    }

    @Override
    public String toString() {
        return "TimelineParticipantFrame{" +
                "championStats=" + championStats +
                ", currentGold=" + currentGold +
                ", damageStats=" + damageStats +
                ", goldPerSecond=" + goldPerSecond +
                ", jungleMinionsKilled=" + jungleMinionsKilled +
                ", level=" + level +
                ", minionsKilled=" + minionsKilled +
                ", participantId=" + participantId +
                ", position=" + position +
                ", timeEnemySpentControlled=" + timeEnemySpentControlled +
                ", totalGold=" + totalGold +
                ", xp=" + xp +
                '}';
    }
}
