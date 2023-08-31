package com.vas.metamindslol.champion;

import com.vas.metamindslol.image.Image;
import jakarta.persistence.*;
import lombok.Data;
import no.stelar7.api.r4j.pojo.lol.staticdata.champion.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Data
public class StaticChampionDD implements Serializable {

    @Id
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    private Image image;
    @Column(length = 100)
    private String key;
    @Column(length = 100)
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private PassiveDD passive;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<StaticChampionSpellDD> spells;
    @Column(length = 100)
    private String title;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StaticChampionDD that = (StaticChampionDD) o;
        return id == that.id &&
                Objects.equals(image, that.image) &&
                Objects.equals(key, that.key) &&
                Objects.equals(name, that.name) &&
                Objects.equals(passive, that.passive) &&
                Objects.equals(spells, that.spells) &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, image, key, name, passive, spells, title);
    }

    @Override
    public String toString() {
        return "StaticChampion{" +
                ", id=" + id +
                ", image=" + image +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", passive=" + passive +
                ", spells=" + spells +
                ", title='" + title + '\'' +
                '}';
    }
}
