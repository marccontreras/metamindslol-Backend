package com.vas.metamindslol.matchV5;

import jakarta.persistence.*;
import lombok.Data;
import no.stelar7.api.r4j.basic.constants.types.lol.TeamType;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.*;

@Entity
@Data
public class MatchTeamDD implements Serializable
{
    private static final long serialVersionUID = 2387712867150164023L;

    @Id
    @GeneratedValue(generator = "MatchTeam-sequence-generator")
    @GenericGenerator(
            name = "MatchTeam-sequence-generator")
    private long id;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<ChampionBanDD>           bans;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    @MapKey(name="objId")
    //might casue problems
    private Map<String, ObjectiveStatsDD> objectives;
    @Enumerated(EnumType.STRING)
    private TeamType                    teamId;
    private boolean                     win;
    
    public List<ChampionBanDD> getBans()
    {
        return bans;
    }
    
    public Map<String, ObjectiveStatsDD> getObjectives()
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
        MatchTeamDD matchTeamDD = (MatchTeamDD) o;
        return win == matchTeamDD.win && Objects.equals(bans, matchTeamDD.bans) && Objects.equals(objectives, matchTeamDD.objectives) && teamId == matchTeamDD.teamId;
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