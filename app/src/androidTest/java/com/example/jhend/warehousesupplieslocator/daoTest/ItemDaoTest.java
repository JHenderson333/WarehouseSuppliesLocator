package com.example.jhend.warehousesupplieslocator.daoTest;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.jhend.warehousesupplieslocator.database.WarehouseDatabase;
import com.example.jhend.warehousesupplieslocator.database.dao.ItemDao;
import com.example.jhend.warehousesupplieslocator.model.Item;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jhend on 1/2/2018.
 */
@RunWith(AndroidJUnit4.class)
public class ItemDaoTest {
    private ItemDao itemDao;
    private WarehouseDatabase warehouseDatabase;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        warehouseDatabase = Room.inMemoryDatabaseBuilder(context, WarehouseDatabase.class).build();
        itemDao = warehouseDatabase.itemDao();

    }

    @After
    public void closeDb() throws IOException {
        itemDao.deleteAll();
        warehouseDatabase.close();
    }


    @Test
    public void testReadWriteDeleteItem() throws Exception {
        Item item = new Item("12-2 Wire", "C00", "On the floor, yellow wire");


        itemDao.insertAll(item);

        List<Item> byName = itemDao.getAll();
        Item dbItem = byName.get(0);

        assertEquals(item.name(), dbItem.name());
        assertEquals(item.cellName(), dbItem.cellName());
        assertEquals(item.descriptor(), dbItem.descriptor());

        itemDao.delete(item);
        List<Item> items = itemDao.getAll();
        assertEquals(0, items.size());
    }

    @Test
    public void testDeleteAll(){
        Item item = new Item("12-2 Wire", "C00", "On the floor, yellow wire");
        itemDao.insertAll(item);
        itemDao.deleteAll();
        List<Item> items = itemDao.getAll();
        assertEquals(0, items.size());
    }

    @Test
    public void testGetItemByName(){
        Item item = new Item("10-2 Wire", "C00", "On the floor, yellow wire");
        itemDao.insertAll(item);
        assertEquals(item.name(), itemDao.getItemByName(item.name()).name());
    }
}
