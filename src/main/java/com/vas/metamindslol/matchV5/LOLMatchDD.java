package com.vas.metamindslol.matchV5;

import jakarta.persistence.*;
import lombok.Data;
import no.stelar7.api.r4j.basic.constants.api.regions.*;
import no.stelar7.api.r4j.basic.constants.types.lol.*;


import java.io.Serializable;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Entity
@Data
public class LOLMatchDD implements Serializable
{
    private static final long serialVersionUID = 6308730651274668047L;
    
    private long                   gameCreation;
    private int                    gameDuration;
    @Id
    private long                   gameId;
    @Enumerated(EnumType.STRING)
    private GameModeType           gameMode;
    private String                 gameName;
    private Long                   gameStartTimestamp;
    private Long                   gameEndTimestamp;
    @Enumerated(EnumType.STRING)
    private GameType               gameType;
    private String                 gameVersion;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<MatchParticipantDD> participants;
    @Enumerated(EnumType.STRING)
    private LeagueShard            platform;
    @Enumerated(EnumType.STRING)
    private GameQueueType queue;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<MatchTeamDD>        teams;



    public ZonedDateTime getMatchCreationAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.gameCreation), ZoneOffset.UTC);
    }

    public Duration getGameDurationAsDuration()
    {
        if (this.gameEndTimestamp != null)
        {
            return Duration.of(this.gameDuration, ChronoUnit.SECONDS);
        }
        
        return Duration.of(this.gameDuration, ChronoUnit.MILLIS);
    }

    public ZonedDateTime getGameStartAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.gameStartTimestamp), ZoneOffset.UTC);
    }
    
    public Long getGameEndTimestamp()
    {
        return gameEndTimestamp;
    }
    
    public ZonedDateTime getGameEndAsDate()
    {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.gameEndTimestamp), ZoneOffset.UTC);
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
        LOLMatchDD lolMatchDD = (LOLMatchDD) o;
        return gameCreation == lolMatchDD.gameCreation && gameDuration == lolMatchDD.gameDuration && gameId == lolMatchDD.gameId && gameMode == lolMatchDD.gameMode && Objects.equals(gameName, lolMatchDD.gameName) && Objects.equals(gameStartTimestamp, lolMatchDD.gameStartTimestamp) && Objects.equals(gameEndTimestamp, lolMatchDD.gameEndTimestamp) && gameType == lolMatchDD.gameType && Objects.equals(gameVersion, lolMatchDD.gameVersion) &&  Objects.equals(participants, lolMatchDD.participants) && platform == lolMatchDD.platform && queue == lolMatchDD.queue && Objects.equals(teams, lolMatchDD.teams);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(gameCreation, gameDuration, gameId, gameMode, gameName, gameStartTimestamp, gameEndTimestamp, gameType, gameVersion, participants, platform, queue, teams);
    }
    
    @Override
    public String toString()
    {
        return "LOLMatch{" +
               "gameCreation=" + gameCreation +
               ", gameDuration=" + gameDuration +
               ", gameId=" + gameId +
               ", gameMode=" + gameMode +
               ", gameName='" + gameName + '\'' +
               ", gameStartTimestamp=" + gameStartTimestamp +
               ", gameEndTimestamp=" + gameEndTimestamp +
               ", gameType=" + gameType +
               ", gameVersion='" + gameVersion + '\'' +
               ", participants=" + participants +
               ", platformId=" + platform +
               ", queueId=" + queue +
               ", teams=" + teams +
               '}';
    }
}
