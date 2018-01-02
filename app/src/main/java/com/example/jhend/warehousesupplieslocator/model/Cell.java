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

    @NonNull
    private int xCoord;

    @NonNull
    private int yCoord;


    public Cell(String name, int xCoord, int yCoord){
        this.name = name;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public Cell() {

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

    public int xCoord(){
        return xCoord;
    }

    public int yCoord(){
        return yCoord;
    }

    public void setXCoord(int xCoord){
        this.xCoord = xCoord;
    }

    public void setYCoord(int yCoord){
        this.yCoord = yCoord;
    }
}
