package com.example.jhend.warehousesupplieslocator.DatabaseManagerTest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.jhend.warehousesupplieslocator.database.DatabaseManager;
import com.example.jhend.warehousesupplieslocator.model.Item;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import static junit.framework.Assert.assertEquals;

/**
 * Created by jhend on 1/3/2018.
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseManagerTest {

    static DatabaseManager dbManager;
    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getTargetContext();
        dbManager = DatabaseManager.getInstance();
        dbManager.loadDatabaseManager(context);

    }
    @Test
    public void testFindItemByPartial(){
        loadItems1();
        ArrayList<String> itemSet = dbManager.findItemsNameList("Carflex");

        assertEquals(1, itemSet.size());
        assertEquals("Carflex", itemSet.get(0));

        itemSet = dbManager.findItemsNameList("Weather-Proof Receptacle");
        assertEquals(2, itemSet.size());
        assertEquals("Weather-Proof Receptacle", itemSet.get(0));
        assertEquals("Weather-Proof Switch", itemSet.get(1));
    }

    @Test
    public void TestFindItemByParialNonExistent(){
        loadItems1();
        ArrayList<String> itemSet = dbManager.findItemsNameList("WireNut");
        assertEquals(0, itemSet.size());
    }
    void loadItems1(){
        dbManager.addItemToDatabase(new Item("Carflex", "C00", "rubber flexible pipe"));
        dbManager.addItemToDatabase(new Item(
                "Weather-Proof Receptacle", "C01", "Receptacle that is weather and tamper resistant"));
        dbManager.addItemToDatabase(new Item(
                "Weather-Proof Switch", "C02", "Switch that is weather and tamper resistant"));
    }

    /**
     * Loads items from a file using a number param
     * @param fileNumber
     */
    void loadItemFile(int fileNumber){
        try {
            Scanner fileScanner = new Scanner(new File("res/testFiles/databaseManagerTest/dbManagerItemList" + fileNumber));
            while(fileScanner.hasNextLine()){
                Scanner lineScanner = new Scanner(fileScanner.nextLine());
                lineScanner.useDelimiter("|");
                Item itemFromScanner = getItemFromScanner(lineScanner);
                dbManager.addItemToDatabase(itemFromScanner);
            }
        }catch (FileNotFoundException e){
            System.out.println("File was not found message: " + e.getMessage());
            String directory = System.getProperty("user.dir");
            System.out.println("Working Directory = " + directory);

        }
    }

    Item getItemFromScanner(Scanner lineScanner){
        lineScanner.useDelimiter("|");
        String itemName = lineScanner.next();
        String cellName = lineScanner.next();
        String description = lineScanner.next();
        return new Item(itemName, cellName, description);
    }

    /**
     * Converts string iterator to ArrayList for testing
     * @param itemList
     */
    ArrayList<String> itemListIteratorToArrayList(Iterator<String> itemList){
        ArrayList<String> itemArrayList = new ArrayList<String>();
        while(itemList.hasNext()){
            itemArrayList.add(itemList.next());
        }
        return itemArrayList;
    }
}

