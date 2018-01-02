package com.example.jhend.warehousesupplieslocator.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
/**
 * @author James D. Henderson
 *
 * Specififes the Contract for the Account Database
 */

public class AccountDatabaseManager {
    private static AccountDatabaseManager instance;

    AccountDatabaseManager(){

    }
    private static Connection databaseConnection;
    public static boolean connectToDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            return false;
        }
        try{

            databaseConnection = DriverManager.getConnection(DatabaseContract.CONNECTION_URI,
                    DatabaseContract.USER_NAME, DatabaseContract.PASSWORD);
        }catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            return false;
        }
        return true;
    }
    public static Boolean createDatabase(){
        try{
            PreparedStatement createStatement = databaseConnection.prepareStatement(DatabaseContract.CREATEDB_STATEMENT);
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
        connectToDatabase();
        return instance;
    }


}
