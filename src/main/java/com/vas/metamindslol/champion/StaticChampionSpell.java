package com.vas.metamindslol.champion;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import no.stelar7.api.r4j.pojo.lol.shared.BaseSpellData;
import no.stelar7.api.r4j.pojo.lol.staticdata.shared.Image;

import java.util.*;

@Entity
public class StaticChampionSpell extends BaseSpellData
{
    private static final long serialVersionUID = 3332883769496451613L;

    @ElementCollection
    private List<Image> altimages;

    @Id
    String name;
    public List<Image> getAltImages()
    {
        return altimages;
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
        if (!super.equals(o))
        {
            return false;
        }
        
        StaticChampionSpell that = (StaticChampionSpell) o;
        
        return Objects.equals(altimages, that.altimages);
    }
    
    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + (altimages != null ? altimages.hashCode() : 0);
        return result;
    }
}
