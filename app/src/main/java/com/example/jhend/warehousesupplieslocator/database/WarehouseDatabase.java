package com.example.jhend.warehousesupplieslocator.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.jhend.warehousesupplieslocator.database.dao.CellDao;
import com.example.jhend.warehousesupplieslocator.database.dao.ItemDao;
import com.example.jhend.warehousesupplieslocator.model.Cell;
import com.example.jhend.warehousesupplieslocator.model.Item;

/**
 * Created by jhend on 1/2/2018.
 */
@Database(entities = {Cell.class, Item.class}, version = 1)
public abstract class WarehouseDatabase extends RoomDatabase {
    public abstract CellDao cellDao();
    public abstract ItemDao itemDao();
}
