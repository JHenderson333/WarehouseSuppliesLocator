package com.example.jhend.warehousesupplieslocator.database;

import android.app.Application;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.jhend.warehousesupplieslocator.model.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by jhend on 1/3/2018.
 */

public class DatabaseManager {
    private static DatabaseManager instance;
    private static HashMap<String, ArrayList<String>> itemNameMap;
    private static WarehouseDatabase warehouseDatabase;
    public static DatabaseManager getInstance(){
        if(instance == null){
            instance = new DatabaseManager();
        }
        return instance;
    }

    public ArrayList<String> findItemsNameList(String partial){
        Scanner partialScanner = new Scanner(partial.toLowerCase());
        ArrayList<String> tokens = new ArrayList<String>();
        HashSet<String> matchingItemNameSet = new HashSet<String>();
        while(partialScanner.hasNext()){
            tokens.add(partialScanner.next());
        }
        for(String token: tokens){
            ArrayList<String> matchingItemNamesForToken = itemNameMap.get(token);
            if(matchingItemNamesForToken != null){
                for(String itemName: matchingItemNamesForToken){
                    matchingItemNameSet.add(itemName);
                }
            }
        }
        return stringSetToArrayList(matchingItemNameSet);
    }
    private ArrayList<String> stringSetToArrayList(HashSet<String> stringSet){
        Iterator<String> iter = stringSet.iterator();
        ArrayList<String> stringList = new ArrayList<String>();
        while(iter.hasNext()){
            stringList.add(iter.next());
        }
        return stringList;
    }
    /**
     * TODO: STUB METHOD UPDATE LATER
     */
    private static void loadItemMap(){
        itemNameMap = new HashMap<String, ArrayList<String>>();
        List<Item> itemList = warehouseDatabase.itemDao().getAll();
        for(Item item : itemList){
            updateItemMap(item.name());
        }
    }
    public static void loadDatabaseManager(Context context){
        loadDatabase(context);
        loadItemMap();
    }
    private static void loadDatabase(Context context){
        warehouseDatabase  = Room.inMemoryDatabaseBuilder(context, WarehouseDatabase.class).build();
    }
    
    public static void addItemToDatabase(Item item){
        warehouseDatabase.itemDao().insertAll(item);
        loadItemMap();
    }

    private static void updateItemMap(String itemName){
        Scanner itemNameScanner = new Scanner(itemName);
        while(itemNameScanner.hasNext()){
            String word = itemNameScanner.next().toLowerCase();

            ArrayList<String> itemNameList = itemNameMap.get(word);
            if(itemNameList == null){
                itemNameList = new ArrayList<String>();
            }
            itemNameList.add(itemName);
            itemNameMap.put(word, itemNameList);
        }
    }
}
