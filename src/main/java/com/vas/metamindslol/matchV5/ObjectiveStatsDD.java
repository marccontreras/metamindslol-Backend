package com.vas.metamindslol.matchV5;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Objects;
@Entity
public class ObjectiveStatsDD implements Serializable
{
    private static final long serialVersionUID = -7533433019007054291L;
    @Id
    @GeneratedValue(generator = "ObjectiveStats-sequence-generator")
    @GenericGenerator(
            name = "ObjectiveStats-sequence-generator")
    private long id;
    private boolean first;
    private int     kills;
    
    public boolean isFirst()
    {
        return first;
    }
    
    public int getKills()
    {
        return kills;
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
        ObjectiveStatsDD that = (ObjectiveStatsDD) o;
        return first == that.first && kills == that.kills;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(first, kills);
    }
    
    @Override
    public String toString()
    {
        return "ObjectiveStat{" +
               "first=" + first +
               ", kills=" + kills +
               '}';
    }
}
