package com.example.jhend.warehousesupplieslocator;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.jhend.warehousesupplieslocator.database.WarehouseDatabase;
import com.example.jhend.warehousesupplieslocator.model.Cell;
import com.example.jhend.warehousesupplieslocator.database.CellDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    private CellDao cellDao;
    private WarehouseDatabase warehouseDatabase;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        warehouseDatabase = Room.inMemoryDatabaseBuilder(context, WarehouseDatabase.class).build();
        cellDao = warehouseDatabase.cellDao();

    }

    @After
    public void closeDb() throws IOException {
        warehouseDatabase.close();
    }

    @Test
    public void writeCellAndReadInList() throws Exception {
        Cell cell = new Cell();
        cell.setName("C00");
        cellDao.insertAll(cell);
        List<Cell> byName = cellDao.getAll();
        assertEquals(cell.name(), byName.get(0).name());
    }
}
