package com.vas.metamindslol.items;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
    @Autowired
    ItemService service;
    @GetMapping(value = "/items")
    public String getItems(){
        return service.getItems();

    }

    @GetMapping(value = "/items/{itemId}")
    public String getItem(@PathVariable Integer itemId){
        return service.getItem(itemId);
    }


}
