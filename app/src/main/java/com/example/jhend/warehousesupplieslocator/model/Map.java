package com.example.jhend.warehousesupplieslocator.model;

import java.util.HashMap;
import java.util.List;

/**
 * Created by jhend on 1/1/2018.
 */

public class Map {
    private List<Cell> cells;
    int cellRows;
    int cellColumns;

    public Map(List<Cell> cells, int cellRows, int cellColumns){
        this.cells = cells;
        this.cellRows = cellRows;
        this.cellColumns = cellColumns;
    }
    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public int getCellRows() {
        return cellRows;
    }

    public void setCellRows(int cellRows) {
        this.cellRows = cellRows;
    }

    public int getCellColumns() {
        return cellColumns;
    }

    public void setCellColumns(int cellColumns) {
        this.cellColumns = cellColumns;
    }






}
