package com.example.jhend.warehousesupplieslocator.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by jhend on 1/2/2018.
 */

@Entity
public class Item {

    @PrimaryKey
    @NonNull
    private String name;

    @NonNull
    private String cellName;

    @NonNull
    private String descriptor;

    public Item(){

    }

    public Item(String name, String cellName, String descriptor){
        this.name = name;
        this.cellName = cellName;
        this.descriptor = descriptor;
    }

    public String name(){
        return name;
    }

    public String cellName(){
        return cellName;
    }

    public String descriptor(){
        return descriptor;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCellName(String cellName){
        this.cellName = cellName;
    }

    public void setDescriptor(String descriptor){
        this.descriptor = descriptor;
    }
}
