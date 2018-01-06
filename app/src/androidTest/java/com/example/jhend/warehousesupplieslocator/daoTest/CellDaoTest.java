package com.example.jhend.warehousesupplieslocator.daoTest;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.jhend.warehousesupplieslocator.database.dao.CellDao;
import com.example.jhend.warehousesupplieslocator.database.WarehouseDatabase;
import com.example.jhend.warehousesupplieslocator.model.Cell;

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
public class CellDaoTest {
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
        cellDao.deleteAll();
        warehouseDatabase.close();
    }


    @Test
    public void testReadWriteDeleteCell() throws Exception {
        Cell cell = new Cell();
        cell.setName("C00");
        cell.setXCoord(0);
        cell.setYCoord(0);

        cellDao.insertAll(cell);

        List<Cell> byName = cellDao.getAll();
        Cell dbCell = byName.get(0);

        assertEquals(cell.name(), dbCell.name());
        assertEquals(cell.xCoord(), dbCell.xCoord());
        assertEquals(cell.yCoord(), dbCell.yCoord());

        cellDao.delete(cell);
        List<Cell> cells = cellDao.getAll();
        assertEquals(0, cells.size());
    }

    @Test
    public void testDeleteAll(){
        Cell cell = new Cell();
        cell.setName("C00");
        cellDao.insertAll(cell);
        cellDao.deleteAll();
        List<Cell> cells = cellDao.getAll();
        assertEquals(0, cells.size());
    }

    @Test
    public void testCellDao(){
        Cell cell = new Cell();
        cell.setName("C02");
        cell.setXCoord(0);
        cell.setYCoord(0);

        cellDao.insertAll(cell);
        assertEquals("C02", cellDao.getCellByName("C02").name());
    }
}
