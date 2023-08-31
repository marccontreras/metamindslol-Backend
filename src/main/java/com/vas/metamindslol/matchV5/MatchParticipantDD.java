package com.vas.metamindslol.matchV5;

import jakarta.persistence.*;
import lombok.Data;
import no.stelar7.api.r4j.basic.constants.types.lol.*;
import no.stelar7.api.r4j.pojo.lol.match.v5.MatchPerks;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.*;

@Entity
@Data
public class MatchParticipantDD implements Serializable {
    private static final long serialVersionUID = 921950638612403583L;

    @Id
    @GeneratedValue(generator = "MatchParticipant-sequence-generator")
    @GenericGenerator(
            name = "MatchParticipant-sequence-generator")
    private long id;
    private int assists;
    private int championId;
    private String championName;
    private int damageDealtToTurrets;
    private int deaths;
    private int goldEarned;
    @Enumerated(EnumType.STRING)
    private LaneType gameDeterminedPosition;
    private int item0;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int item6;
    private int kills;
    @Enumerated(EnumType.STRING)
    private LaneType lane;
    private int magicDamageDealtToChampions;
    private int neutralMinionsKilled;
    private int participantId;
    private int physicalDamageDealtToChampions;
    private int profileIcon;
    private String puuid;
    @Enumerated(EnumType.STRING)
    private RoleType role;
    private int summoner1Id;
    private int summoner2Id;
    private int summoner1Casts;
    private int summoner2Casts;
    private String summonerId;
    private int summonerLevel;
    private String summonerName;
    @Enumerated(EnumType.STRING)
    private TeamType team;
    @Enumerated(EnumType.STRING)
    private LaneType championSelectLane;
    private int totalDamageDealtToChampions;
    private int totalMinionsKilled;
    private int totalDamageTaken;
    private int trueDamageDealtToChampions;
    private int visionScore;
    private boolean win;
    private String summonerSpell1;
    private String summonerSpell2;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MatchParticipantDD that = (MatchParticipantDD) o;
        return assists == that.assists && championId == that.championId && damageDealtToTurrets == that.damageDealtToTurrets && deaths == that.deaths && goldEarned == that.goldEarned && item0 == that.item0 && item1 == that.item1 && item2 == that.item2 && item3 == that.item3 && item4 == that.item4 && item5 == that.item5 && item6 == that.item6 && kills == that.kills && magicDamageDealtToChampions == that.magicDamageDealtToChampions && neutralMinionsKilled == that.neutralMinionsKilled && participantId == that.participantId && physicalDamageDealtToChampions == that.physicalDamageDealtToChampions && profileIcon == that.profileIcon && summoner1Id == that.summoner1Id && summoner2Id == that.summoner2Id &&  summoner1Casts == that.summoner1Casts && summoner2Casts == that.summoner2Casts && summonerLevel == that.summonerLevel && totalDamageDealtToChampions == that.totalDamageDealtToChampions && totalDamageTaken == that.totalDamageTaken && totalMinionsKilled == that.totalMinionsKilled && trueDamageDealtToChampions == that.trueDamageDealtToChampions && visionScore == that.visionScore && win == that.win &&  Objects.equals(championName, that.championName) && gameDeterminedPosition == that.gameDeterminedPosition && lane == that.lane && Objects.equals(puuid, that.puuid) && role == that.role && Objects.equals(summonerId, that.summonerId) && Objects.equals(summonerName, that.summonerName) && team == that.team && championSelectLane == that.championSelectLane;
    }

    @Override
    public int hashCode() {
        return Objects.hash(assists, championId, championName, damageDealtToTurrets, deaths, goldEarned, gameDeterminedPosition, item0, item1, item2, item3, item4, item5, item6, kills, lane, magicDamageDealtToChampions, neutralMinionsKilled, participantId, physicalDamageDealtToChampions, profileIcon, puuid, role,  summoner1Id,  summoner2Id, summoner1Casts, summoner2Casts, summonerId, summonerLevel, summonerName, team, championSelectLane, totalDamageDealtToChampions, totalDamageTaken, totalMinionsKilled, trueDamageDealtToChampions, visionScore, win);
    }

    @Override
    public String toString() {
        return "MatchParticipant{" +
                "assists=" + assists +
                ", championId=" + championId +
                ", championName='" + championName + '\'' +
                ", damageDealtToTurrets=" + damageDealtToTurrets +
                ", deaths=" + deaths +
                ", goldEarned=" + goldEarned +
                ", individualPosition=" + gameDeterminedPosition +
                ", item0=" + item0 +
                ", item1=" + item1 +
                ", item2=" + item2 +
                ", item3=" + item3 +
                ", item4=" + item4 +
                ", item5=" + item5 +
                ", item6=" + item6 +
                ", kills=" + kills +
                ", lane=" + lane +
                ", magicDamageDealtToChampions=" + magicDamageDealtToChampions +
                ", neutralMinionsKilled=" + neutralMinionsKilled +
                ", participantId=" + participantId +
                ", physicalDamageDealtToChampions=" + physicalDamageDealtToChampions +
                ", profileIcon=" + profileIcon +
                ", puuid='" + puuid + '\'' +
                ", role=" + role +
                ", summoner1Id=" + summoner1Id +
                ", summoner2Id=" + summoner2Id +
                ", summoner1Casts=" + summoner1Casts +
                ", summoner2Casts=" + summoner2Casts +
                ", summonerSpell1=" + summonerSpell1 +
                ", summonerSpell2=" + summonerSpell2 +
                ", summonerId='" + summonerId + '\'' +
                ", summonerLevel=" + summonerLevel +
                ", summonerName='" + summonerName + '\'' +
                ", teamId=" + team +
                ", teamPosition=" + championSelectLane +
                ", totalDamageDealtToChampions=" + totalDamageDealtToChampions +
                ", totalDamageTaken=" + totalDamageTaken +
                ", totalMinionsKilled=" + totalMinionsKilled +
                ", trueDamageDealtToChampions=" + trueDamageDealtToChampions +
                ", visionScore=" + visionScore +
                ", win=" + win +
                '}';
    }
}
