package com.vas.metamindslol.item;

import com.vas.metamindslol.items.ItemService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemDDServiceTest {
    @Autowired
    ItemService service;

    @Test
    public void testgetItems() {

        Assertions.assertNotNull(service.getItems());
    }

    @Test
    public void testgetItem() {
        Assert.assertNotNull(service.getItem(4629));
    }
}
