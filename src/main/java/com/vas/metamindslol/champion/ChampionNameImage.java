package com.vas.metamindslol.champion;

import lombok.Data;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ChampionNameImage implements Comparable<ChampionNameImage> {

    private String name;
    private String image;

    public ChampionNameImage(String name, String image) {
        this.name = name;
        this.image = image;
    }

    @Override
    public int compareTo(ChampionNameImage o) {
        return this.name.compareTo(o.getName());
    }

    //public String toString() {}

}
