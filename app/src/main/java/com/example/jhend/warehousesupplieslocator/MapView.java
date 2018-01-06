package com.example.jhend.warehousesupplieslocator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.LocalSocketAddress;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.jhend.warehousesupplieslocator.model.Cell;
import com.example.jhend.warehousesupplieslocator.model.Map;

import org.w3c.dom.Attr;

import java.util.List;

/**
 * Created by jhend on 1/5/2018.
 */

public class MapView extends View {
    private float viewHeight;
    private float viewWidth;
    private float mapXPos;
    private float mapYPos;
    private float cellWidth;
    private float cellHeight;
    private float cellsWide;
    private float cellsTall;
    private Map cellMap;
    private boolean highlightCell = false;
    private boolean mapUpdated = false;
    private Cell highlightedCell;

    public void setCellWidth(float cellWidth) {
        this.cellWidth = cellWidth;
    }

    public void setCellHeight(float cellHeight) {
        this.cellHeight = cellHeight;
    }

    public void setCellsWide(float cellsWide) {
        this.cellsWide = cellsWide;
    }

    public void setCellsTall(float cellsTall) {
        this.cellsTall = cellsTall;
    }

    public void setViewHeight(float viewHeight) {
        this.viewHeight = viewHeight;
    }

    public MapView(Context context, AttributeSet attrs){
        super(context, attrs);
        int viewWidthIndex = getAttributeIndexByName(attrs, "layout_width");
        int viewHeightIndex = getAttributeIndexByName(attrs, "layout_height");

        String widthString = attrs.getAttributeValue(viewWidthIndex);
        String heightString = attrs.getAttributeValue(viewHeightIndex);

        viewWidth = attributeValueToFloat(widthString);
        viewHeight = attributeValueToFloat(heightString);

        mapXPos = 0;
        mapYPos = 0;

    }
    public void loadView(Canvas canvas){


    }
    @Override
    protected void onDraw( Canvas canvas ) {
        if(highlightCell){
            highlightCell(canvas);
        }
        if(cellMap != null){
            drawCells(canvas);
            mapUpdated = true;
        }
    }

    private void highlightCell(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        drawCell(canvas, highlightedCell, paint);
    }

    public void setHighlightCell(Cell highlightedCell){
        this.highlightedCell = highlightedCell;
        highlightCell = true;
        invalidate();
    }

    public float viewHeight() {
        return viewHeight;
    }
    public float viewWidth(){
        return viewWidth;
    }

    public float xPos() {
        return mapXPos;
    }

    public float yPos(){
        return mapYPos;
    }

    private int getAttributeIndexByName(AttributeSet attrs, String attribute_name){
        int attributeIndex = 0;
        try{
            while(!attrs.getAttributeName(attributeIndex).equals(attribute_name)){
                attributeIndex++;
            }
            return attributeIndex;
        }catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return -1;
    }

    private float attributeValueToFloat(String attribute){
        StringBuilder floatBuilder = new StringBuilder();
        for(int index = 0; index < attribute.length(); index++){
            if(!Character.isDigit(attribute.charAt(index)) && attribute.charAt(index) != '.'){
                return Float.valueOf(floatBuilder.toString());
            }
            floatBuilder.append(attribute.charAt(index));
        }
        return 0.0f;
    }


    public void setViewWidth(float viewWidth) {
        this.viewWidth = viewWidth;
    }

    public void setMapXPos(float mapXPos) {
        this.mapXPos = mapXPos;
    }

    public void setMapYPos(float mapYPos) {
        this.mapYPos = mapYPos;
    }

    private void drawCells(Canvas canvas){
        Paint cellPaint = new Paint();
        cellPaint.setColor(Color.DKGRAY);
        cellPaint.setStrokeWidth(4);
        cellPaint.setStyle(Paint.Style.STROKE);
        for(Cell cell : cellMap.getCells()){
            if(highlightedCell == null || !cell.name().equals(highlightedCell.name()))
                drawCell(canvas, cell, cellPaint);
        }

    }

    private void drawCell(Canvas canvas, Cell cell, Paint cellPaint){
        float cellLeftPos = cellWidth* cell.xCoord();
        float cellTopPos = cellHeight* cell.yCoord();
        float cellRightPos = cellLeftPos + cellWidth;
        float cellBottomPos = cellTopPos + cellHeight;
        canvas.drawRect(cellLeftPos, cellTopPos, cellRightPos, cellBottomPos, cellPaint);
    }

    public void setCellMap(Map cellMap){
        this.cellMap = cellMap;
        setCellHeightFromMap();
        setCellWidthFromMap();
        invalidate();
    }

    private void setCellHeightFromMap(){
        cellHeight = (viewHeight/cellMap.getCellRows())*2.45f; //For some reason need a modifier of (insert here) to fit view;
    }

    private void setCellWidthFromMap(){
        cellWidth = (viewWidth/cellMap.getCellColumns())*2.45f; //For some reason need a modifier of (insert here) to fit view;
    }
}
