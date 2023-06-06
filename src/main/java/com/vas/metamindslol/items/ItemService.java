package com.vas.metamindslol.items;

import no.stelar7.api.r4j.pojo.lol.staticdata.item.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.vas.metamindslol.R4JInstance.dDragonAPI;

@Service
public class ItemService {

    /**
     *
     * @return The name and image route of the items ordered by name
     */
    public  List<ItemNameImage> getItems() {
        List<ItemNameImage> items= new ArrayList<>();

        dDragonAPI.getItems().forEach((k,v)-> items.add(new ItemNameImage(v.getName(),v.getImage().getFull())));
        Collections.sort( items);
        return items;
    }

    /**
     *
     * @param itemId
     * @return The item asked by parameter
     */
    public  Item getItem(Integer itemId) {

         return dDragonAPI.getItem(itemId);
    }

    //TODO: when there's the search-bar, be sure to pass the name and image of the champ(maybe even wr)
}
