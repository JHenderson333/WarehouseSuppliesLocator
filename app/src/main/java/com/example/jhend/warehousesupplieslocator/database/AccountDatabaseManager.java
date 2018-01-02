package com.example.jhend.warehousesupplieslocator.database;

import android.provider.ContactsContract;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * @author James D. Henderson
 *
 * Specififes the Contract for the Account Database
 */

public class AccountDatabaseManager {
    private static AccountDatabaseManager instance;

    AccountDatabaseManager(){

    }
    Connection databaseConnection;
    public boolean connectToDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            return false;
        }
        try{

            databaseConnection = DriverManager.getConnection(DatabaseContract.connectionURI,
                    DatabaseContract.userName, DatabaseContract.password);
        }catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            return false;
        }
        return true;
    }
    public static AccountDatabaseManager getInstance(){
        if(instance == null){
            instance = new AccountDatabaseManager();
        }
        return instance;
    }
}
