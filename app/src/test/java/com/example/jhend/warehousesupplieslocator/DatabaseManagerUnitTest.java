package com.example.jhend.warehousesupplieslocator;

import com.example.jhend.warehousesupplieslocator.database.DatabaseManager;
import com.example.jhend.warehousesupplieslocator.model.Item;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.Assert.assertEquals;

/**
 * Created by jhend on 1/3/2018.
 */

public class DatabaseManagerUnitTest {
    DatabaseManager dbManager;
    @Before
    public void setUp() {
        dbManager = DatabaseManager.getInstance();

    }
    @Test
    public void testFindItemByPartial(){
        loadSampleMap();
        ArrayList<Item> itemList = dbManager.findItemsByName("Carflex");
        assertEquals("Gray flexible pipe", itemList.get(0).descriptor());

        itemList = dbManager.findItemsByName("Weather-Proof");
        assertEquals("Small box With Name on Front of Box", itemList.get(0).descriptor());
    }


    public void loadSampleMap(){
        HashMap<String, ArrayList<Item>> itemMap = new HashMap<String, ArrayList<Item>>();

        Item item1 = new Item("Carflex", "C00", "Gray flexible pipe");
        ArrayList<Item> list1 = new ArrayList<Item>();
        list1.add(item1);

        Item item2 = new Item("Weather-Proof Receptacle", "C01", "Small box With Name on Front of Box");
        ArrayList<Item> list2 = new ArrayList<Item>();
        list2.add(item2);

        Item item3 = new Item("Circuit Breaker 40AMP", "C02", "Look for thin long box with black devices");
        ArrayList<Item> list3 = new ArrayList<Item>();
        list3.add(item3);



        itemMap.put("Carflex", list1);
        itemMap.put("Weather-Proof", list2);
        itemMap.put("Circuit", list3);
        dbManager.stubHashMap(itemMap);
    }
}
