package com.example.jhend.warehousesupplieslocator.model;

/**
 * Created by jhend on 1/1/2018.
 */

public class Cell {
    String name;
    public boolean setName(String newName){
        if(!validName(newName)){
            return false;
        }
        name = newName;
        return true;
    }
    public boolean validName(String name){
        if(name.length() < 3){
            return false;
        }
        else if(!Character.isLetter(name.charAt(0))){
            return false;
        }
        return false;
    }
    public String name() {
        return name;
    }
}
