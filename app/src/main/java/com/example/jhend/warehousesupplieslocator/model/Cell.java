package com.example.jhend.warehousesupplieslocator.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;

import java.lang.Object;
/**
 * Created by jhend on 1/1/2018.
 */

@Entity
public class Cell {
    @PrimaryKey
    @NonNull
    private String name;

    public Cell(){
        //No arg constructor for ORMLite
    }
    public void setName(String newName){
       /**
        if(!validName(newName)){
            return false;
        }*/
        this.name = newName;
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
