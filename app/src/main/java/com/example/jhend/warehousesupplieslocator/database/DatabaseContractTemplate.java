package com.example.jhend.warehousesupplieslocator.database;

/**
 * Created by jhend on 1/1/2018.
 */

public class DatabaseContractTemplate {
    public static String CONNECTION_URI = "jdbc:mysql://localhost:3306/warehouseApp?createDatabaseIfNotExist=true";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "";
    public static final String CREATEDB_STATEMENT = "CREATE DATABASE IF NOT EXISTS WAREHOUSEAPP";
}
