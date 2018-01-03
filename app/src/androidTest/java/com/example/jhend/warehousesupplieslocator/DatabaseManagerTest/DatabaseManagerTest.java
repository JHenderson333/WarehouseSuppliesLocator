package com.example.jhend.warehousesupplieslocator.DatabaseManagerTest;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.jhend.warehousesupplieslocator.database.WarehouseDatabase;
import com.example.jhend.warehousesupplieslocator.database.dao.ItemDao;
import com.example.jhend.warehousesupplieslocator.model.Item;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.io.IOException;

/**
 * Created by jhend on 1/3/2018.
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseManagerTest {
    Context context;
    WarehouseDatabase warehouseDatabase;
    ItemDao itemDao;
    Item item1, item2, item3;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        warehouseDatabase = Room.inMemoryDatabaseBuilder(context, WarehouseDatabase.class).build();
        itemDao = warehouseDatabase.itemDao();


    }

    @After
    public void closeDb() throws IOException {
        warehouseDatabase.close();
    }

    public void testFindItemsByName(){
        Item item1 = new Item("Carflex", "C00", "Gray flexible pipe");
        Item item2 = new Item("Weather-Proof Receptacle", "C01", "Small box With Name on Front of Box");
        Item item3 = new Item("Circuit Breaker 40AMP", "C02", "Look for thin long box with black devices");

        itemDao.insertAll(item1, item2, item3);
    }

    public void loadItemsIntoDatabase(){

    }
}
