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
    private int baronKills;
    private int bountyLevel;
    @Convert(converter = ChallengesConverter.class)
    @Column(length = 65000)
    private Map<String, Object> challenges;
    private int champExperience;
    private int champLevel;
    private int championId;
    private String championName;
    private int championTransform;
    private int consumablesPurchased;
    private int damageDealtToBuildings;
    private int damageDealtToObjectives;
    private int damageDealtToTurrets;
    private int damageSelfMitigated;
    private int deaths;
    private int detectorWardsPlaced;
    private int doubleKills;
    private int dragonKills;
    private boolean firstBloodAssist;
    private boolean firstBloodKill;
    private boolean firstTowerAssist;
    private boolean firstTowerKill;
    private boolean gameEndedInEarlySurrender;
    private boolean gameEndedInSurrender;
    private int goldEarned;
    private int goldSpent;
    @Enumerated(EnumType.STRING)
    private LaneType gameDeterminedPosition;
    private int inhibitorKills;
    private int inhibitorTakedowns;
    private int inhibitorsLost;
    private int item0;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int item6;
    private int itemsPurchased;
    private int killingSprees;
    private int kills;
    @Enumerated(EnumType.STRING)

    private LaneType lane;
    private int largestCriticalStrike;
    private int largestKillingSpree;
    private int largestMultiKill;
    private int longestTimeSpentLiving;
    private int magicDamageDealt;
    private int magicDamageDealtToChampions;
    private int magicDamageTaken;
    private int neutralMinionsKilled;
    private int nexusKills;
    private int nexusLost;
    private int nexusTakedowns;
    private int objectivesStolen;
    private int objectivesStolenAssists;
    private int participantId;
    private int pentaKills;
    //runes, TODO: LATER
    @Transient
    private MatchPerks perks;
    private int physicalDamageDealt;
    private int physicalDamageDealtToChampions;
    private int physicalDamageTaken;
    private int profileIcon;
    private String puuid;
    private int quadraKills;
    private String riotIdName;
    private String riotIdTagline;
    @Enumerated(EnumType.STRING)
    private RoleType role;
    private int sightWardsBoughtInGame;
    private int spell1Casts;
    private int summoner1Id;
    private int spell2Casts;
    private int summoner2Id;
    private int spell3Casts;
    private int spell4Casts;
    private int summoner1Casts;
    private int summoner2Casts;
    private String summonerId;
    private int summonerLevel;
    private String summonerName;
    private boolean teamEarlySurrendered;
    @Enumerated(EnumType.STRING)
    private TeamType team;
    @Enumerated(EnumType.STRING)
    private LaneType championSelectLane;
    private int timeCCingOthers;
    private int timePlayed;
    private int totalDamageDealt;
    private int totalDamageDealtToChampions;
    private int totalDamageShieldedOnTeammates;
    private int totalDamageTaken;
    private int totalHeal;
    private int totalHealsOnTeammates;
    private int totalMinionsKilled;
    private int totalTimeCCDealt;
    private int totalTimeSpentDead;
    private int totalUnitsHealed;
    private int tripleKills;
    private int trueDamageDealt;
    private int trueDamageDealtToChampions;
    private int trueDamageTaken;
    private int turretKills;
    private int turretTakedowns;
    private int turretsLost;
    private int unrealKills;
    private int visionScore;
    private int visionWardsBoughtInGame;
    private int wardsKilled;
    private int wardsPlaced;
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
        return assists == that.assists && baronKills == that.baronKills && bountyLevel == that.bountyLevel && champExperience == that.champExperience && champLevel == that.champLevel && championId == that.championId && championTransform == that.championTransform && consumablesPurchased == that.consumablesPurchased && damageDealtToBuildings == that.damageDealtToBuildings && damageDealtToObjectives == that.damageDealtToObjectives && damageDealtToTurrets == that.damageDealtToTurrets && damageSelfMitigated == that.damageSelfMitigated && deaths == that.deaths && detectorWardsPlaced == that.detectorWardsPlaced && doubleKills == that.doubleKills && dragonKills == that.dragonKills && firstBloodAssist == that.firstBloodAssist && firstBloodKill == that.firstBloodKill && firstTowerAssist == that.firstTowerAssist && firstTowerKill == that.firstTowerKill && gameEndedInEarlySurrender == that.gameEndedInEarlySurrender && gameEndedInSurrender == that.gameEndedInSurrender && goldEarned == that.goldEarned && goldSpent == that.goldSpent && inhibitorKills == that.inhibitorKills && inhibitorTakedowns == that.inhibitorTakedowns && inhibitorsLost == that.inhibitorsLost && item0 == that.item0 && item1 == that.item1 && item2 == that.item2 && item3 == that.item3 && item4 == that.item4 && item5 == that.item5 && item6 == that.item6 && itemsPurchased == that.itemsPurchased && killingSprees == that.killingSprees && kills == that.kills && largestCriticalStrike == that.largestCriticalStrike && largestKillingSpree == that.largestKillingSpree && largestMultiKill == that.largestMultiKill && longestTimeSpentLiving == that.longestTimeSpentLiving && magicDamageDealt == that.magicDamageDealt && magicDamageDealtToChampions == that.magicDamageDealtToChampions && magicDamageTaken == that.magicDamageTaken && neutralMinionsKilled == that.neutralMinionsKilled && nexusKills == that.nexusKills && nexusLost == that.nexusLost && nexusTakedowns == that.nexusTakedowns && objectivesStolen == that.objectivesStolen && objectivesStolenAssists == that.objectivesStolenAssists && participantId == that.participantId && pentaKills == that.pentaKills && physicalDamageDealt == that.physicalDamageDealt && physicalDamageDealtToChampions == that.physicalDamageDealtToChampions && physicalDamageTaken == that.physicalDamageTaken && profileIcon == that.profileIcon && quadraKills == that.quadraKills && sightWardsBoughtInGame == that.sightWardsBoughtInGame && spell1Casts == that.spell1Casts && summoner1Id == that.summoner1Id && spell2Casts == that.spell2Casts && summoner2Id == that.summoner2Id && spell3Casts == that.spell3Casts && spell4Casts == that.spell4Casts && summoner1Casts == that.summoner1Casts && summoner2Casts == that.summoner2Casts && summonerLevel == that.summonerLevel && teamEarlySurrendered == that.teamEarlySurrendered && timeCCingOthers == that.timeCCingOthers && timePlayed == that.timePlayed && totalDamageDealt == that.totalDamageDealt && totalDamageDealtToChampions == that.totalDamageDealtToChampions && totalDamageShieldedOnTeammates == that.totalDamageShieldedOnTeammates && totalDamageTaken == that.totalDamageTaken && totalHeal == that.totalHeal && totalHealsOnTeammates == that.totalHealsOnTeammates && totalMinionsKilled == that.totalMinionsKilled && totalTimeCCDealt == that.totalTimeCCDealt && totalTimeSpentDead == that.totalTimeSpentDead && totalUnitsHealed == that.totalUnitsHealed && tripleKills == that.tripleKills && trueDamageDealt == that.trueDamageDealt && trueDamageDealtToChampions == that.trueDamageDealtToChampions && trueDamageTaken == that.trueDamageTaken && turretKills == that.turretKills && turretTakedowns == that.turretTakedowns && turretsLost == that.turretsLost && unrealKills == that.unrealKills && visionScore == that.visionScore && visionWardsBoughtInGame == that.visionWardsBoughtInGame && wardsKilled == that.wardsKilled && wardsPlaced == that.wardsPlaced && win == that.win && Objects.equals(challenges, that.challenges) && Objects.equals(championName, that.championName) && gameDeterminedPosition == that.gameDeterminedPosition && lane == that.lane && Objects.equals(perks, that.perks) && Objects.equals(puuid, that.puuid) && Objects.equals(riotIdName, that.riotIdName) && Objects.equals(riotIdTagline, that.riotIdTagline) && role == that.role && Objects.equals(summonerId, that.summonerId) && Objects.equals(summonerName, that.summonerName) && team == that.team && championSelectLane == that.championSelectLane;
    }

    @Override
    public int hashCode() {
        return Objects.hash(assists, baronKills, bountyLevel, challenges, champExperience, champLevel, championId, championName, championTransform, consumablesPurchased, damageDealtToBuildings, damageDealtToObjectives, damageDealtToTurrets, damageSelfMitigated, deaths, detectorWardsPlaced, doubleKills, dragonKills, firstBloodAssist, firstBloodKill, firstTowerAssist, firstTowerKill, gameEndedInEarlySurrender, gameEndedInSurrender, goldEarned, goldSpent, gameDeterminedPosition, inhibitorKills, inhibitorTakedowns, inhibitorsLost, item0, item1, item2, item3, item4, item5, item6, itemsPurchased, killingSprees, kills, lane, largestCriticalStrike, largestKillingSpree, largestMultiKill, longestTimeSpentLiving, magicDamageDealt, magicDamageDealtToChampions, magicDamageTaken, neutralMinionsKilled, nexusKills, nexusLost, nexusTakedowns, objectivesStolen, objectivesStolenAssists, participantId, pentaKills, perks, physicalDamageDealt, physicalDamageDealtToChampions, physicalDamageTaken, profileIcon, puuid, quadraKills, riotIdName, riotIdTagline, role, sightWardsBoughtInGame, spell1Casts, summoner1Id, spell2Casts, summoner2Id, spell3Casts, spell4Casts, summoner1Casts, summoner2Casts, summonerId, summonerLevel, summonerName, teamEarlySurrendered, team, championSelectLane, timeCCingOthers, timePlayed, totalDamageDealt, totalDamageDealtToChampions, totalDamageShieldedOnTeammates, totalDamageTaken, totalHeal, totalHealsOnTeammates, totalMinionsKilled, totalTimeCCDealt, totalTimeSpentDead, totalUnitsHealed, tripleKills, trueDamageDealt, trueDamageDealtToChampions, trueDamageTaken, turretKills, turretTakedowns, turretsLost, unrealKills, visionScore, visionWardsBoughtInGame, wardsKilled, wardsPlaced, win);
    }

    @Override
    public String toString() {
        return "MatchParticipant{" +
                "assists=" + assists +
                ", baronKills=" + baronKills +
                ", bountyLevel=" + bountyLevel +
                ", challenges=" + challenges +
                ", champExperience=" + champExperience +
                ", champLevel=" + champLevel +
                ", championId=" + championId +
                ", championName='" + championName + '\'' +
                ", championTransform=" + championTransform +
                ", consumablesPurchased=" + consumablesPurchased +
                ", damageDealtToBuildings=" + damageDealtToBuildings +
                ", damageDealtToObjectives=" + damageDealtToObjectives +
                ", damageDealtToTurrets=" + damageDealtToTurrets +
                ", damageSelfMitigated=" + damageSelfMitigated +
                ", deaths=" + deaths +
                ", detectorWardsPlaced=" + detectorWardsPlaced +
                ", doubleKills=" + doubleKills +
                ", dragonKills=" + dragonKills +
                ", firstBloodAssist=" + firstBloodAssist +
                ", firstBloodKill=" + firstBloodKill +
                ", firstTowerAssist=" + firstTowerAssist +
                ", firstTowerKill=" + firstTowerKill +
                ", gameEndedInEarlySurrender=" + gameEndedInEarlySurrender +
                ", gameEndedInSurrender=" + gameEndedInSurrender +
                ", goldEarned=" + goldEarned +
                ", goldSpent=" + goldSpent +
                ", individualPosition=" + gameDeterminedPosition +
                ", inhibitorKills=" + inhibitorKills +
                ", inhibitorTakedowns=" + inhibitorTakedowns +
                ", inhibitorsLost=" + inhibitorsLost +
                ", item0=" + item0 +
                ", item1=" + item1 +
                ", item2=" + item2 +
                ", item3=" + item3 +
                ", item4=" + item4 +
                ", item5=" + item5 +
                ", item6=" + item6 +
                ", itemsPurchased=" + itemsPurchased +
                ", killingSprees=" + killingSprees +
                ", kills=" + kills +
                ", lane=" + lane +
                ", largestCriticalStrike=" + largestCriticalStrike +
                ", largestKillingSpree=" + largestKillingSpree +
                ", largestMultiKill=" + largestMultiKill +
                ", longestTimeSpentLiving=" + longestTimeSpentLiving +
                ", magicDamageDealt=" + magicDamageDealt +
                ", magicDamageDealtToChampions=" + magicDamageDealtToChampions +
                ", magicDamageTaken=" + magicDamageTaken +
                ", neutralMinionsKilled=" + neutralMinionsKilled +
                ", nexusKills=" + nexusKills +
                ", nexusLost=" + nexusLost +
                ", nexusTakedowns=" + nexusTakedowns +
                ", objectivesStolen=" + objectivesStolen +
                ", objectivesStolenAssists=" + objectivesStolenAssists +
                ", participantId=" + participantId +
                ", pentaKills=" + pentaKills +
                ", perks=" + perks +
                ", physicalDamageDealt=" + physicalDamageDealt +
                ", physicalDamageDealtToChampions=" + physicalDamageDealtToChampions +
                ", physicalDamageTaken=" + physicalDamageTaken +
                ", profileIcon=" + profileIcon +
                ", puuid='" + puuid + '\'' +
                ", quadraKills=" + quadraKills +
                ", riotIdName='" + riotIdName + '\'' +
                ", riotIdTagline='" + riotIdTagline + '\'' +
                ", role=" + role +
                ", sightWardsBoughtInGame=" + sightWardsBoughtInGame +
                ", spell1Casts=" + spell1Casts +
                ", summoner1Id=" + summoner1Id +
                ", spell2Casts=" + spell2Casts +
                ", summoner2Id=" + summoner2Id +
                ", spell3Casts=" + spell3Casts +
                ", spell4Casts=" + spell4Casts +
                ", summoner1Casts=" + summoner1Casts +
                ", summoner2Casts=" + summoner2Casts +
                ", summonerSpell1=" + summonerSpell1 +
                ", summonerSpell2=" + summonerSpell2 +
                ", summonerId='" + summonerId + '\'' +
                ", summonerLevel=" + summonerLevel +
                ", summonerName='" + summonerName + '\'' +
                ", teamEarlySurrendered=" + teamEarlySurrendered +
                ", teamId=" + team +
                ", teamPosition=" + championSelectLane +
                ", timeCCingOthers=" + timeCCingOthers +
                ", timePlayed=" + timePlayed +
                ", totalDamageDealt=" + totalDamageDealt +
                ", totalDamageDealtToChampions=" + totalDamageDealtToChampions +
                ", totalDamageShieldedOnTeammates=" + totalDamageShieldedOnTeammates +
                ", totalDamageTaken=" + totalDamageTaken +
                ", totalHeal=" + totalHeal +
                ", totalHealsOnTeammates=" + totalHealsOnTeammates +
                ", totalMinionsKilled=" + totalMinionsKilled +
                ", totalTimeCCDealt=" + totalTimeCCDealt +
                ", totalTimeSpentDead=" + totalTimeSpentDead +
                ", totalUnitsHealed=" + totalUnitsHealed +
                ", tripleKills=" + tripleKills +
                ", trueDamageDealt=" + trueDamageDealt +
                ", trueDamageDealtToChampions=" + trueDamageDealtToChampions +
                ", trueDamageTaken=" + trueDamageTaken +
                ", turretKills=" + turretKills +
                ", turretTakedowns=" + turretTakedowns +
                ", turretsLost=" + turretsLost +
                ", unrealKills=" + unrealKills +
                ", visionScore=" + visionScore +
                ", visionWardsBoughtInGame=" + visionWardsBoughtInGame +
                ", wardsKilled=" + wardsKilled +
                ", wardsPlaced=" + wardsPlaced +
                ", win=" + win +
                '}';
    }
}
