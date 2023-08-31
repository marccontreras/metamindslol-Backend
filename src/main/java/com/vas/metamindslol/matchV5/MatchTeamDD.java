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
    @Enumerated(EnumType.STRING)
    private TeamType                    teamId;
    private boolean                     win;

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
        return win == matchTeamDD.win && teamId == matchTeamDD.teamId;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash( teamId, win);
    }
    
    @Override
    public String toString()
    {
        return "MatchTeam{" +
               ", teamId=" + teamId +
               ", win=" + win +
               '}';
    }
}
