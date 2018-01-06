package com.example.jhend.warehousesupplieslocator.database;

import android.app.Application;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import com.example.jhend.warehousesupplieslocator.model.Cell;
import com.example.jhend.warehousesupplieslocator.model.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

/**
 * Created by jhend on 1/3/2018.
 */

public class DatabaseManager {
    private static DatabaseManager instance;
    private static HashMap<String, ArrayList<String>> itemNameMap;
    private static HashMap<String, Item> itemMap;
    private static HashMap<String, Cell> cellMap;
    private static WarehouseDatabase warehouseDatabase;
    private static boolean isLoaded = false;

    public static DatabaseManager getInstance(){
        if(instance == null){
            synchronized (DatabaseManager.class){
                if(instance == null) {
                    instance = new DatabaseManager();
                }
            }

        }
        return instance;
    }
    public ArrayList<String> findItemsNameList(String partial){
        Scanner partialScanner = new Scanner(partial.toLowerCase());
        ArrayList<String> searchWords = new ArrayList<String>();
        HashMap<String, Integer> itemOccuranceMap = new HashMap<String, Integer>();
        while(partialScanner.hasNext()){
            searchWords.add(partialScanner.next());
        }
        for(String searchWord: searchWords){
            ArrayList<String> matchingItemNamesForSearchWord = itemNameMap.get(searchWord);
            if(matchingItemNamesForSearchWord != null){
                for(String itemName: matchingItemNamesForSearchWord){
                    Integer count = itemOccuranceMap.get(itemName);
                    if(count == null){
                        itemOccuranceMap.put(itemName, 1);
                    }
                    else{
                        itemOccuranceMap.put(itemName, count + 1);
                    }
                }
            }
        }
        return stringMapToArrayList(itemOccuranceMap);
    }
    private ArrayList<String> stringMapToArrayList(HashMap<String, Integer> itemOccuranceMap){
        List<Entry<String, Integer>> listOfEntries = hashMapToSortedEntryList(itemOccuranceMap);
        ArrayList<String> stringList = new ArrayList<String>();
        for(Entry<String, Integer> entry : listOfEntries){
            stringList.add(entry.getKey());
        }
        return stringList;
    }

    /**
     * Code modified from: http://www.java67.com/2015/01/how-to-sort-hashmap-in-java-based-on.html
     * Takes the hashmap with the item name and occurrence and sorts them by occurrence and creates a
     * List of entries
     * @param itemOccurenceMap
     * @return
     */
    private List<Entry<String, Integer>> hashMapToSortedEntryList
            (HashMap<String, Integer> itemOccurenceMap){
        Set<Entry<String,Integer>> itemSet = itemOccurenceMap.entrySet();

        List<Entry<String, Integer>> listOfEntries = new ArrayList<Entry<String, Integer>>(itemSet);
        Collections.sort(listOfEntries, valueComparator);

        return listOfEntries;
    }
    Comparator<Entry<String, Integer>> valueComparator = new Comparator<Entry<String,Integer>>() {

        @Override
        public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
            Integer v1 = e1.getValue();
            Integer v2 = e2.getValue();
            return v2.compareTo(v1);
        }
    };
    /**
     * TODO: STUB METHOD UPDATE LATER
     */
    private static void loadItemNameMap(){
        itemNameMap = new HashMap<String, ArrayList<String>>();
        List<Item> itemList = warehouseDatabase.itemDao().getAll();
        for(Item item : itemList){
            updateItemMap(item.name());
        }
    }
    public static void loadDatabaseManager(final Context context){
        if(isLoaded)
            return;
        getInstance();
        loadDatabase(context);
        itemMap = new HashMap<String, Item>();
        cellMap = new HashMap<String, Cell>();
        loadItemNameMap();
        isLoaded = true;

    }
    private static void loadDatabase(Context context){
        warehouseDatabase  = Room.inMemoryDatabaseBuilder(context, WarehouseDatabase.class).build();
    }
    
    public static void addItemToDatabase(Item item){
        warehouseDatabase.itemDao().insertAll(item);
        itemMap.put(item.name(), item);
        loadItemNameMap();
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

    public static void addCellToDatabase(Cell c) {
        warehouseDatabase.cellDao().insertAll(c);
        cellMap.put(c.name(), c);
    }

    public static List<Cell> getCells(){
        return warehouseDatabase.cellDao().getAll();
    }

    public static List<Item> getItems(){
        return warehouseDatabase.itemDao().getAll();
    }


    public static Item getItemByName(String itemName){
        return itemMap.get(itemName);
    }

    public static Cell getCellByName(String cellName){
        return cellMap.get(cellName);
    }
}
