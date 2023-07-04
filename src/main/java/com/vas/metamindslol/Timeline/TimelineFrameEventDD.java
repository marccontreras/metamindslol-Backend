package com.vas.metamindslol.Timeline;

import jakarta.persistence.*;
import lombok.Data;
import no.stelar7.api.r4j.basic.constants.types.*;
import no.stelar7.api.r4j.basic.constants.types.lol.*;
import no.stelar7.api.r4j.pojo.lol.match.v5.TimelineDamageData;
import no.stelar7.api.r4j.pojo.lol.match.v5.TimelinePosition;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.time.*;
import java.util.*;

@Entity
@Data
public class TimelineFrameEventDD implements Serializable {
    @Serial
    private static final long serialVersionUID = -653418574112361358L;

    @Id
    @GeneratedValue(generator = "timelineFrameEvent-sequence-generator")
    @GenericGenerator(
            name = "timelineFrameEvent-sequence-generator")
    private long id;

    @Enumerated(EnumType.STRING)
    private BuildingType buildingType;

    @Enumerated(EnumType.STRING)
    private EventType type;

    @Enumerated(EnumType.STRING)
    private KillType killType;

    @Enumerated(EnumType.STRING)
    private LaneType laneType;

    @Enumerated(EnumType.STRING)
    private LevelUpType levelUpType;
    //look up how to change it in the future
    @ElementCollection
    private List<Integer> assistingParticipantIds;
    private transient List<TimelineDamageData> victimDamageDealt;
    private transient List<TimelineDamageData> victimDamageReceived;
    @Enumerated(EnumType.STRING)
    private MonsterSubType monsterSubType;
    @Enumerated(EnumType.STRING)
    private MonsterType monsterType;
    @Enumerated(EnumType.ORDINAL)

    private TeamType killerTeamId;
    @Enumerated(EnumType.ORDINAL)

    private TeamType teamId;
    @Enumerated(EnumType.ORDINAL)

    private TeamType winningTeam;
    //might need it in the future
    private transient TimelinePosition position;
    @Enumerated(EnumType.STRING)
    private TowerType towerType;
    @Enumerated(EnumType.STRING)
    private TransformType transformType;
    @Enumerated(EnumType.STRING)
    private WardType wardType;
    private int actualStartTime;
    private int afterId;
    private int beforeId;
    private int bounty;

    private int creatorId;
    private int goldGain;
    private int itemId;
    private int killStreakLength;
    private int killerId;
    private int level;
    private int multiKillLength;
    private int participantId;
    private int shutdownBounty;
    private int skillSlot;
    private int victimId;
    private long gameId;
    private long realTimestamp;

    private long timestamp;

    public long getRealTimestamp() {
        return realTimestamp;
    }

    public ZonedDateTime getTimestampAsDate() {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.realTimestamp), ZoneOffset.UTC);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public EventType getType() {
        return type;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public int getParticipantId() {
        return participantId;
    }

    public int getSkillSlot() {
        return skillSlot;
    }

    public LevelUpType getLevelUpType() {
        return levelUpType;
    }

    public int getItemId() {
        return itemId;
    }

    public int getBeforeId() {
        return beforeId;
    }

    public int getAfterId() {
        return afterId;
    }

    public int getGoldGain() {
        return goldGain;
    }

    public WardType getWardType() {
        return wardType;
    }

    public int getBounty() {
        return bounty;
    }

    public int getKillStreakLength() {
        return killStreakLength;
    }

    public int getKillerId() {
        return killerId;
    }

    public TimelinePosition getPosition() {
        return position;
    }

    public List<TimelineDamageData> getVictimDamageDealt() {
        return victimDamageDealt == null ? Collections.emptyList() : victimDamageDealt;
    }

    public List<TimelineDamageData> getVictimDamageReceived() {
        return victimDamageReceived == null ? Collections.emptyList() : victimDamageReceived;
    }

    public int getVictimId() {
        return victimId;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public TowerType getTowerType() {
        return towerType;
    }

    public LaneType getLaneType() {
        return laneType;
    }

    public List<Integer> getAssistingParticipantIds() {
        return assistingParticipantIds;
    }

    public MonsterType getMonsterType() {
        return monsterType;
    }

    public MonsterSubType getMonsterSubType() {
        return monsterSubType;
    }

    public TeamType getTeamId() {
        return teamId;
    }

    public TeamType getKillerTeamId() {
        return killerTeamId;
    }

    public TransformType getTransformType() {
        return transformType;
    }

    public KillType getKillType() {
        return killType;
    }

    public int getShutdownBounty() {
        return shutdownBounty;
    }

    public int getLevel() {
        return level;
    }

    public int getMultiKillLength() {
        return multiKillLength;
    }

    public int getActualStartTime() {
        return actualStartTime;
    }

    public long getGameId() {
        return gameId;
    }

    public TeamType getWinningTeam() {
        return winningTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimelineFrameEventDD that = (TimelineFrameEventDD) o;
        return creatorId == that.creatorId && realTimestamp == that.realTimestamp && timestamp == that.timestamp && participantId == that.participantId && skillSlot == that.skillSlot && itemId == that.itemId && beforeId == that.beforeId && afterId == that.afterId && goldGain == that.goldGain && bounty == that.bounty && killStreakLength == that.killStreakLength && killerId == that.killerId && victimId == that.victimId && shutdownBounty == that.shutdownBounty && level == that.level && multiKillLength == that.multiKillLength && actualStartTime == that.actualStartTime && gameId == that.gameId && type == that.type && levelUpType == that.levelUpType && wardType == that.wardType && Objects.equals(position, that.position) && Objects.equals(victimDamageDealt, that.victimDamageDealt) && Objects.equals(victimDamageReceived, that.victimDamageReceived) && buildingType == that.buildingType && towerType == that.towerType && laneType == that.laneType && Objects.equals(assistingParticipantIds, that.assistingParticipantIds) && monsterType == that.monsterType && monsterSubType == that.monsterSubType && teamId == that.teamId && killerTeamId == that.killerTeamId && transformType == that.transformType && killType == that.killType && winningTeam == that.winningTeam;
    }

    @Override
    public int hashCode() {
        return Objects.hash(creatorId, realTimestamp, timestamp, type, participantId, skillSlot, levelUpType, itemId, beforeId, afterId, goldGain, wardType, bounty, killStreakLength, killerId, position, victimDamageDealt, victimDamageReceived, victimId, buildingType, towerType, laneType, assistingParticipantIds, monsterType, monsterSubType, teamId, killerTeamId, transformType, killType, shutdownBounty, level, multiKillLength, actualStartTime, gameId, winningTeam);
    }

    @Override
    public String toString() {
        return "TimelineFrameEvent{" +
                "creatorId=" + creatorId +
                ", realTimestamp=" + realTimestamp +
                ", timestamp=" + timestamp +
                ", type=" + type +
                ", participantId=" + participantId +
                ", skillSlot=" + skillSlot +
                ", levelUpType=" + levelUpType +
                ", itemId=" + itemId +
                ", beforeId=" + beforeId +
                ", afterId=" + afterId +
                ", goldGain=" + goldGain +
                ", wardType=" + wardType +
                ", bounty=" + bounty +
                ", killStreakLength=" + killStreakLength +
                ", killerId=" + killerId +
                ", position=" + position +
                ", victimDamageDealt=" + victimDamageDealt +
                ", victimDamageReceived=" + victimDamageReceived +
                ", victimId=" + victimId +
                ", buildingType=" + buildingType +
                ", towerType=" + towerType +
                ", laneType=" + laneType +
                ", assistingParticipantIds=" + assistingParticipantIds +
                ", monsterType=" + monsterType +
                ", monsterSubType=" + monsterSubType +
                ", teamId=" + teamId +
                ", killerTeamId=" + killerTeamId +
                ", transformType=" + transformType +
                ", killType=" + killType +
                ", shutdownBounty=" + shutdownBounty +
                ", level=" + level +
                ", multiKillLength=" + multiKillLength +
                ", actualStartTime=" + actualStartTime +
                ", gameId=" + gameId +
                ", winningTeam=" + winningTeam +
                '}';
    }
}
