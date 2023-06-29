package com.vas.metamindslol.matchV5;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
public class ChampionBanDD implements Serializable
{

    @Serial
    private static final long serialVersionUID = 9036737379955122323L;
    //there will be a total of number of champions x10(pickTurn)

    @Id
    @GeneratedValue(generator = "ChampionBan-sequence-generator")
    @GenericGenerator(
            name = "ChampionBan-sequence-generator")
    private long id;
    private int championId;
    @Range(min =0, max =10)
    private int pickTurn;
    
    public int getChampionId()
    {
        return championId;
    }
    
    public int getPickTurn()
    {
        return pickTurn;
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
        ChampionBanDD that = (ChampionBanDD) o;
        return championId == that.championId && pickTurn == that.pickTurn;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(championId, pickTurn);
    }
    
    @Override
    public String toString()
    {
        return "ChampionBan{" +
               "championId=" + championId +
               ", pickTurn=" + pickTurn +
               '}';
    }
}
