package com.example.jhend.warehousesupplieslocator.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jhend.warehousesupplieslocator.MapView;
import com.example.jhend.warehousesupplieslocator.R;
import com.example.jhend.warehousesupplieslocator.database.DatabaseManager;
import com.example.jhend.warehousesupplieslocator.model.Cell;
import com.example.jhend.warehousesupplieslocator.model.Item;
import com.example.jhend.warehousesupplieslocator.model.Map;
import com.example.jhend.warehousesupplieslocator.utility.ActivityUtils;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ItemLocator extends AppCompatActivity {
    Button searchButton;
    DatabaseManager databaseManager;
    MapView mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_locator);
        searchButton = (Button)findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ItemSearch.class);
                intent.setAction(Intent.ACTION_SEARCH);
                startActivity(intent);

            }
        });



        databaseManager = ActivityUtils.getDatabaseManagerForActivity(getApplicationContext());

        addItemDatabase("Carflex 1 in", "C00", "Flexible rubber pipe");
        addItemDatabase("Carflex 2 in", "C00", "Flexible rubber pipe");
        addItemDatabase("Carflex 1.5 in", "C00", "Flexible rubber pipe");
        addItemDatabase("Carflex .75 in", "C00", "Flexible rubber pipe");
        
        loadMap();
        handleSearchedItem();
    }

    void handleSearchedItem(){
        Bundle extras = getIntent().getExtras();
        if(extras == null){
            return;
        }
        String itemName = extras.get("item_name").toString();
        Cell cell = getCellForItem(itemName);
        mapView.setHighlightCell(cell);
        if(itemName == null){
            return;
        }

    }
    Item getItemFromName(String itemName){
        return null;
    }

    public void loadMap(){
        mapView = findViewById(R.id.mapView);
        //MapView mapView = new MapView(getApplicationContext(), 100.0f, 100.0f, 0.0f, 0.0f);
        //viewPort = mapView;
        mapView.setBackgroundColor(Color.RED);
        loadSampleCellMap();
    }


    private void loadSampleCellMap(){
        loadSampleCells();
        List<Cell> cells = null;
        try {
            cells = new AsyncTask<Void, Void, List<Cell>>(){
                protected List<Cell> doInBackground(Void... voids) {
                    return databaseManager.getCells();
                }
                protected void onPostExecute(DatabaseManager result){

                }
            }.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Map sampleCellMap = new Map(cells, 15, 15);
        mapView.setCellMap(sampleCellMap);
    }
    private void loadSampleCells(){
        new AsyncTask<Void, Void, Void> (){
            protected Void doInBackground (Void...voids){
                Cell c = new Cell("C00", 0, 15);
                databaseManager.addCellToDatabase(c);
                c = new Cell("C01", 0, 14);
                databaseManager.addCellToDatabase(c);
                c = new Cell("C02", 0, 13);
                databaseManager.addCellToDatabase(c);
                c = new Cell("D00" , 15, 12);
                databaseManager.addCellToDatabase(c);
                c = new Cell("D01" , 15, 11);
                databaseManager.addCellToDatabase(c);
                return null;
            }
        }.execute();
    }

    private Cell getCellForItem(final String itemName){
        Cell cell = null;
        try {
            cell = new AsyncTask<Void, Void, Cell>(){
                protected Cell doInBackground(Void... voids) {
                    Item item = DatabaseManager.getItemByName(itemName); //TODO HANDLE ITEM NOT FOUND
                    Log.d("db size", ""+databaseManager.getItems().size());
                    String cellName = item.cellName();
                    return databaseManager.getCellByName(cellName);
                }
                protected void onPostExecute(DatabaseManager result){

                }
            }.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return cell;
    }



    void addItemDatabase(final String itemName, final String cellName, final String Description){
        new AsyncTask<Void, Void, Void>(){
            protected Void doInBackground(Void... voids) {
                databaseManager.addItemToDatabase(new Item(itemName, cellName, Description));
                return null;
            }
        }.execute();

    }
}
