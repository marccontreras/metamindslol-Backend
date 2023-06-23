package com.vas.metamindslol.items;

import com.vas.metamindslol.ModelMapperConfig;
import com.vas.metamindslol.R4JInstance;
import com.vas.metamindslol.champion.ChampionNameImage;
import com.vas.metamindslol.champion.ChampionRepository;
import com.vas.metamindslol.champion.StaticChampion;
import no.stelar7.api.r4j.impl.lol.raw.DDragonAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.vas.metamindslol.GsonInstance.gson;

@Service
public class ItemService {

    @Autowired
    private ModelMapperConfig modelMapper;

    @Autowired
    ItemRepository itemRepository;
    DDragonAPI dDragonAPI= R4JInstance.dDragonAPI;

    /**
     *
     * @return The name and image route of the items ordered by name
     */

    public String getItems() {
        List<ItemNameImage> items= new ArrayList<>();
        List<Item>itemsDB= itemRepository.findAll();
        if(itemsDB.isEmpty()) {
            itemsDB= loadItems();
        }
        itemsDB.forEach((i) ->items.add(new ItemNameImage(i.getName(),i.getImage().getFull())));
        Collections.sort( items);
        return gson.toJson(items);
    }

    public List<Item> loadItems() {

        Map<Integer, no.stelar7.api.r4j.pojo.lol.staticdata.item.Item> itemsDdragon=dDragonAPI.getItems();
        List<no.stelar7.api.r4j.pojo.lol.staticdata.item.Item> list = new ArrayList<>(itemsDdragon.values());

        List<Item> itemsList= new ModelMapperConfig().mapAsList(list,Item.class);
        return itemRepository.saveAll(itemsList);
    }

    /**
     *
     * @param itemId
     * @return The item asked by parameter
     */
    public  String getItem(Integer itemId) {
        Item item= null;
        Optional<Item> opItem= itemRepository.findById(itemId);
        if(opItem.isEmpty()){
            no.stelar7.api.r4j.pojo.lol.staticdata.item.Item itemD= dDragonAPI.getItem(itemId);
            item=modelMapper.map(itemD,Item.class);
            itemRepository.save(item);
        }else{
            item=opItem.get();
        }
        return gson.toJson(item);
    }
    //TODO: when there's the search-bar, be sure to pass the name and image of the champ(maybe even wr)
}
