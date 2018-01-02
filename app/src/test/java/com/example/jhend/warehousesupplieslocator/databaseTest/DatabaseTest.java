package com.example.jhend.warehousesupplieslocator.databaseTest;


import com.example.jhend.warehousesupplieslocator.database.AccountDatabaseManager;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;


/**
 * Created by jhend on 1/1/2018.
 */

public class DatabaseTest{
    AccountDatabaseManager databaseContract;
    @Before
    public void setUp(){
        databaseContract = AccountDatabaseManager.getInstance();
    }
    @Test
    public void testGetInstance(){
        AccountDatabaseManager dbContract = AccountDatabaseManager.getInstance();
        assertNotNull(dbContract);
    }
    @Test
    public void testConnection(){
        assertTrue(databaseContract.connectToDatabase());
    }

    @Test
    public void testCreateDatabase(){
        assertTrue(databaseContract.createDatabase());
    }

}
