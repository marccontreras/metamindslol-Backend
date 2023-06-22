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
public class LOLMatch implements Serializable
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
    @Enumerated(EnumType.STRING)
    private MapType                mapId;
    @OneToMany
    private List<MatchParticipant> participants;
    @Enumerated(EnumType.STRING)
    private LeagueShard            platformId;
    @Enumerated(EnumType.STRING)
    private GameQueueType          queueId;
    @OneToMany
    private List<MatchTeam>        teams;
    private String                 tournamentCode;
    

//    public LOLTimeline getTimeline()
//    {
//        return MatchV5API.getInstance().getTimeline(platformId.toRegionShard(), platformId + "_" + gameId);
//    }
    
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
        LOLMatch lolMatch = (LOLMatch) o;
        return gameCreation == lolMatch.gameCreation && gameDuration == lolMatch.gameDuration && gameId == lolMatch.gameId && gameMode == lolMatch.gameMode && Objects.equals(gameName, lolMatch.gameName) && Objects.equals(gameStartTimestamp, lolMatch.gameStartTimestamp) && Objects.equals(gameEndTimestamp, lolMatch.gameEndTimestamp) && gameType == lolMatch.gameType && Objects.equals(gameVersion, lolMatch.gameVersion) && mapId == lolMatch.mapId && Objects.equals(participants, lolMatch.participants) && platformId == lolMatch.platformId && queueId == lolMatch.queueId && Objects.equals(teams, lolMatch.teams) && Objects.equals(tournamentCode, lolMatch.tournamentCode);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(gameCreation, gameDuration, gameId, gameMode, gameName, gameStartTimestamp, gameEndTimestamp, gameType, gameVersion, mapId, participants, platformId, queueId, teams, tournamentCode);
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
               ", mapId=" + mapId +
               ", participants=" + participants +
               ", platformId=" + platformId +
               ", queueId=" + queueId +
               ", teams=" + teams +
               ", tournamentCode='" + tournamentCode + '\'' +
               '}';
    }
}
