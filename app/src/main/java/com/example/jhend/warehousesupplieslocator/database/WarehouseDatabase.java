package com.example.jhend.warehousesupplieslocator.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.jhend.warehousesupplieslocator.model.Cell;
import com.example.jhend.warehousesupplieslocator.model.CellDao;

/**
 * Created by jhend on 1/2/2018.
 */
@Database(entities = {Cell.class}, version = 1)
public abstract class WarehouseDatabase extends RoomDatabase {
    public abstract CellDao cellDao();

}
