package com.vas.metamindslol.items;

import com.google.gson.Gson;
import com.vas.metamindslol.GsonInstance;
import com.vas.metamindslol.ModelMapperConfig;
import com.vas.metamindslol.R4JInstance;
import no.stelar7.api.r4j.impl.lol.raw.DDragonAPI;
import no.stelar7.api.r4j.pojo.lol.staticdata.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ItemService {

    @Autowired
    private ModelMapperConfig modelMapper;

    @Autowired
    ItemRepository itemRepository;

    Gson gson = GsonInstance.getInstance().getGson();
    DDragonAPI dDragonAPI= R4JInstance.dDragonAPI;

    /**
     *
     * @return The name and image route of the items ordered by name
     */

    public String getItems() {
        List<ItemNameImage> items= new ArrayList<>();
        List<ItemDD>itemsDB= itemRepository.findAll();
        if(itemsDB.isEmpty()) {
            itemsDB= loadItems();
        }
        itemsDB.forEach((i) ->items.add(new ItemNameImage(i.getName(),i.getImage().getFull())));
        Collections.sort( items);
        return gson.toJson(items);
    }

    public List<ItemDD> loadItems() {

        Map<Integer, Item> itemsDdragon=dDragonAPI.getItems();
        List<Item> list = new ArrayList<>(itemsDdragon.values());

        List<ItemDD> itemsList= new ModelMapperConfig().mapAsList(list, ItemDD.class);
        return itemRepository.saveAll(itemsList);
    }

    /**
     *
     * @param itemId
     * @return The item asked by parameter
     */
    public  String getItem(Integer itemId) {
        ItemDD itemDD = null;
        Optional<ItemDD> opItem= itemRepository.findById(itemId);
        if(opItem.isEmpty()){
            Item itemD= dDragonAPI.getItem(itemId);
            itemDD =modelMapper.map(itemD, ItemDD.class);
            itemRepository.save(itemDD);
        }else{
            itemDD =opItem.get();
        }
        return gson.toJson(itemDD);
    }
    //TODO: when there's the search-bar, be sure to pass the name and image of the champ(maybe even wr)
}
