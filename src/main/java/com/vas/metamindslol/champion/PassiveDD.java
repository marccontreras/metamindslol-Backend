package com.vas.metamindslol.champion;

import com.vas.metamindslol.image.Image;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.io.Serializable;
@Entity
public class PassiveDD implements Serializable
{
    private static final long serialVersionUID = -1431514724519691319L;
    
    private String description;
    @OneToOne (cascade = CascadeType.ALL)
    private Image image;
    @Id
    private String name;
    
    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass() != obj.getClass())
        {
            return false;
        }
        final PassiveDD other = (PassiveDD) obj;
        if (this.description == null)
        {
            if (other.description != null)
            {
                return false;
            }
        } else if (!this.description.equals(other.description))
        {
            return false;
        }
        if (this.image == null)
        {
            if (other.image != null)
            {
                return false;
            }
        } else if (!this.image.equals(other.image))
        {
            return false;
        }
        if (this.name == null)
        {
            return other.name == null;
        } else
        {
            return this.name.equals(other.name);
        }
    }
    
    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription()
    {
        return this.description;
    }
    
    /**
     * Gets the image.
     *
     * @return the image
     */
    public Image getImage()
    {
        return this.image;
    }
    
    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Gets the sanitized description.
     *
     * @return the sanitized description
     */
    public String getSanitizedDescription()
    {
        return sanitize(this.description);
    }
    
    
    private String sanitize(String inData)
    {
        String outData = inData;
        outData = outData.replaceAll("<br>", "\n");
        outData = outData.replaceAll("<.+?>", "");
        return outData;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.description == null) ? 0 : this.description.hashCode());
        result = (prime * result) + ((this.image == null) ? 0 : this.image.hashCode());
        result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "Passive{" +
               "description='" + description + '\'' +
               ", image=" + image +
               ", name='" + name + '\'' +
               '}';
    }
}
