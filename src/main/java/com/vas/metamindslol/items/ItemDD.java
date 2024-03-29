package com.vas.metamindslol.items;

import jakarta.persistence.*;
import lombok.Data;
import no.stelar7.api.r4j.pojo.lol.staticdata.shared.Image;
import no.stelar7.api.r4j.pojo.lol.staticdata.shared.MetaData;

import java.io.Serializable;
import java.util.*;
@Entity
@Data
public class ItemDD implements Serializable
{
    private static final long serialVersionUID = 7919515074046086833L;
    @ElementCollection
    @Column(length=10000)
    private Map<String, String>  effect;
    private String               colloq;
    private boolean              consumeOnFull;
    private boolean              consumed;
    private int                  depth;
    @Column(length=10000)
    private String               description;
    @ElementCollection
    @Column(name="itemFrom", length = 1000)
    private List<String>         from;
    @OneToOne (cascade = CascadeType.ALL)
    private GoldDD goldDD;
    @Column(name="itemGroup",length = 1000)
    private String               group;
    private boolean              hideFromAll;
    @Id
    private int                  id;
    private Image image;
    private boolean              inStore;
    @ElementCollection
    @Column(name="itemInto", length = 100)

    private List<String>         into;
    @ElementCollection
    private Map<String, Boolean> maps;
    private String               name;
    @Column(length=10000)
    private String               plaintext;
    private String               requiredChampion;
    private MetaData rune;
    private int                  specialRecipe;
    private int                  stacks;
    @OneToOne (cascade = CascadeType.ALL)
    private InventoryDataStatsDD stats;
    @ElementCollection
    private List<String>         tags;
    
    /**
     * Gets effect.
     *
     * @return the effect
     */
    public Map<String, String> getEffect()
    {
        return effect;
    }
    
    /**
     * Gets colloq.
     *
     * @return the colloq
     */
    public String getColloq()
    {
        return colloq;
    }
    
    /**
     * Is consume on full boolean.
     *
     * @return the boolean
     */
    public boolean isConsumeOnFull()
    {
        return consumeOnFull;
    }
    
    /**
     * Is consumed boolean.
     *
     * @return the boolean
     */
    public boolean isConsumed()
    {
        return consumed;
    }
    
    /**
     * Gets depth.
     *
     * @return the depth
     */
    public int getDepth()
    {
        return depth;
    }
    
    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Gets from.
     *
     * @return the from
     */
    public List<String> getFrom()
    {
        return from;
    }
    
    /**
     * Gets gold.
     *
     * @return the gold
     */
    public GoldDD getGoldDD()
    {
        return goldDD;
    }
    
    /**
     * Gets group.
     *
     * @return the group
     */
    public String getGroup()
    {
        return group;
    }
    
    /**
     * Is hide from all boolean.
     *
     * @return the boolean
     */
    public boolean isHideFromAll()
    {
        return hideFromAll;
    }
    
    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * Gets image.
     *
     * @return the image
     */
    public Image getImage()
    {
        return image;
    }
    
    /**
     * Is in store boolean.
     *
     * @return the boolean
     */
    public boolean isInStore()
    {
        return inStore;
    }
    
    /**
     * Gets into.
     *
     * @return the into
     */
    public List<String> getInto()
    {
        return into;
    }
    
    /**
     * Gets maps.
     *
     * @return the maps
     */
    public Map<String, Boolean> getMaps()
    {
        return maps;
    }
    
    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Gets plaintext.
     *
     * @return the plaintext
     */
    public String getPlaintext()
    {
        return plaintext;
    }
    
    /**
     * Gets required champion.
     *
     * @return the required champion
     */
    public String getRequiredChampion()
    {
        return requiredChampion;
    }
    
    /**
     * Gets rune.
     *
     * @return the rune
     */
    public MetaData getRune()
    {
        return rune;
    }
    
    /**
     * Gets sanitized description.
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
    
    /**
     * Gets special recipe.
     *
     * @return the special recipe
     */
    public int getSpecialRecipe()
    {
        return specialRecipe;
    }
    
    /**
     * Gets stacks.
     *
     * @return the stacks
     */
    public int getStacks()
    {
        return stacks;
    }
    
    /**
     * Gets stats.
     *
     * @return the stats
     */
    public InventoryDataStatsDD getStats()
    {
        return stats;
    }
    
    /**
     * Gets tags.
     *
     * @return the tags
     */
    public List<String> getTags()
    {
        return tags;
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
        ItemDD itemDD = (ItemDD) o;
        return consumeOnFull == itemDD.consumeOnFull &&
               consumed == itemDD.consumed &&
               depth == itemDD.depth &&
               hideFromAll == itemDD.hideFromAll &&
               id == itemDD.id &&
               inStore == itemDD.inStore &&
               specialRecipe == itemDD.specialRecipe &&
               stacks == itemDD.stacks &&
               Objects.equals(effect, itemDD.effect) &&
               Objects.equals(colloq, itemDD.colloq) &&
               Objects.equals(description, itemDD.description) &&
               Objects.equals(from, itemDD.from) &&
               Objects.equals(goldDD, itemDD.goldDD) &&
               Objects.equals(group, itemDD.group) &&
               Objects.equals(image, itemDD.image) &&
               Objects.equals(into, itemDD.into) &&
               Objects.equals(maps, itemDD.maps) &&
               Objects.equals(name, itemDD.name) &&
               Objects.equals(plaintext, itemDD.plaintext) &&
               Objects.equals(requiredChampion, itemDD.requiredChampion) &&
               Objects.equals(rune, itemDD.rune) &&
               Objects.equals(stats, itemDD.stats) &&
               Objects.equals(tags, itemDD.tags);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(effect, colloq, consumeOnFull, consumed, depth, description, from, goldDD, group, hideFromAll, id, image, inStore, into, maps, name, plaintext, requiredChampion, rune, specialRecipe, stacks, stats, tags);
    }
    
    @Override
    public String toString()
    {
        return "Item{" +
               "effect=" + effect +
               ", colloq='" + colloq + '\'' +
               ", consumeOnFull=" + consumeOnFull +
               ", consumed=" + consumed +
               ", depth=" + depth +
               ", description='" + description + '\'' +
               ", from=" + from +
               ", gold=" + goldDD +
               ", group='" + group + '\'' +
               ", hideFromAll=" + hideFromAll +
               ", id=" + id +
               ", image=" + image +
               ", inStore=" + inStore +
               ", into=" + into +
               ", maps=" + maps +
               ", name='" + name + '\'' +
               ", plaintext='" + plaintext + '\'' +
               ", requiredChampion='" + requiredChampion + '\'' +
               ", rune=" + rune +
               ", specialRecipe=" + specialRecipe +
               ", stacks=" + stacks +
               ", stats=" + stats +
               ", tags=" + tags +
               '}';
    }
}
