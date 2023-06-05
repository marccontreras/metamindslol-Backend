package com.vas.metamindslol.champion;

import lombok.Data;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ChampionNameImage implements Serializable {
    @Serial
    private static final long serialVersionUID = 0;
    private String name;
    private String image;

    public ChampionNameImage(String name, String image) {
        this.name = name;
        this.image = image;
    }

    //public String toString() {}

}
