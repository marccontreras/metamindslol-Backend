package com.vas.metamindslol.champion;

import com.vas.metamindslol.image.Image;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Data
//the extends values will be null unless the class is also mapped in the project
public class StaticChampionSpellDD //extends BaseSpellData
{
    private static final long serialVersionUID = 3332883769496451613L;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<Image> altimages;

    @OneToOne (cascade = CascadeType.ALL)
    private Image image;
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
        
        StaticChampionSpellDD that = (StaticChampionSpellDD) o;
        
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
