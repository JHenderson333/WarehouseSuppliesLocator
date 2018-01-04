package com.example.jhend.warehousesupplieslocator.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jhend.warehousesupplieslocator.R;
import com.example.jhend.warehousesupplieslocator.database.DatabaseManager;

public class ItemLocator extends AppCompatActivity {
    Button searchButton;
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
    }
}
