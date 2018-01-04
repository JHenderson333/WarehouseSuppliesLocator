package com.example.jhend.warehousesupplieslocator.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jhend.warehousesupplieslocator.R;
import com.example.jhend.warehousesupplieslocator.database.DatabaseManager;
import com.example.jhend.warehousesupplieslocator.model.Item;
import com.example.jhend.warehousesupplieslocator.utility.ActivityUtils;

public class ItemLocator extends AppCompatActivity {
    Button searchButton;
    DatabaseManager databaseManager;
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
        handleSearchedItem();
    }

    void handleSearchedItem(){
        Bundle extras = getIntent().getExtras();
        if(extras == null){
            return;
        }
        String itemName = extras.get("item_name").toString();
        if(itemName == null){
            return;
        }

    }
    Item getItemFromName(String itemName){
        return null;
    }

    private void loadMap(){

    }
}
