package com.example.jhend.warehousesupplieslocator.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Room;

import com.example.jhend.warehousesupplieslocator.model.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by jhend on 1/3/2018.
 */

public class DatabaseManager {
    private static DatabaseManager instance;
    private HashMap<String, ArrayList<Item>> itemMap;
    private WarehouseDatabase warehouseDatabase;
    public static DatabaseManager getInstance(){
        if(instance == null){
            instance = new DatabaseManager();
        }
        return instance;
    }

    public ArrayList<Item> findItemsByName(String partial){
        Scanner partialScanner = new Scanner(partial);
        ArrayList<String> tokens = new ArrayList<String>();
        ArrayList<Item> matchingItems = new ArrayList<Item>();
        while(partialScanner.hasNext()){
            tokens.add(partialScanner.next());
        }
        for(String token: tokens){
            matchingItems.addAll(itemMap.get(token));
        }
        return matchingItems;
    }

    public void updateDataLists(String partial){

    }

    private void loadDatabase(){
    }

    /**
     * TODO: STUB METHOD DELETE LATER
     */
    public void stubHashMap(HashMap<String, ArrayList<Item>> itemMap){
        this.itemMap = itemMap;
    }
}
