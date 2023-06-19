package com.vas.metamindslol.match;

import com.vas.metamindslol.Timeline.LOLTimeline;
import jakarta.persistence.*;
import no.stelar7.api.r4j.basic.constants.types.lol.TeamType;
import no.stelar7.api.r4j.pojo.lol.match.v5.ChampionBan;
import no.stelar7.api.r4j.pojo.lol.match.v5.ObjectiveStats;

import java.io.Serializable;
import java.util.*;

public class MatchTeam implements Serializable
{
    private static final long serialVersionUID = 2387712867150164023L;

    @PrimaryKeyJoinColumn
    @ManyToOne
    @Id
    private LOLMatch match;
    @ElementCollection
    private List<ChampionBan>           bans;
    @ElementCollection
    private Map<String, ObjectiveStats> objectives;
    @Enumerated(EnumType.STRING)
    @Id
    private TeamType                    teamId;
    private boolean                     win;
    
    public List<ChampionBan> getBans()
    {
        return bans;
    }
    
    public Map<String, ObjectiveStats> getObjectives()
    {
        return objectives;
    }
    
    public TeamType getTeamId()
    {
        return teamId;
    }
    
    public boolean didWin()
    {
        return win;
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
        MatchTeam matchTeam = (MatchTeam) o;
        return win == matchTeam.win && Objects.equals(bans, matchTeam.bans) && Objects.equals(objectives, matchTeam.objectives) && teamId == matchTeam.teamId;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(bans, objectives, teamId, win);
    }
    
    @Override
    public String toString()
    {
        return "MatchTeam{" +
               "bans=" + bans +
               ", objectives=" + objectives +
               ", teamId=" + teamId +
               ", win=" + win +
               '}';
    }
}
