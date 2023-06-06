package com.vas.metamindslol.items;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ItemNameImage implements Serializable,Comparable<ItemNameImage> {
    @Serial
    private static final long serialVersionUID = 0;
    private String name;
    private String image;

    public ItemNameImage(String name, String image) {
        this.name = name;
        this.image = image;
    }

    @Override
    public int compareTo(ItemNameImage o) {
        return this.name.compareTo(o.getName());
    }


}
