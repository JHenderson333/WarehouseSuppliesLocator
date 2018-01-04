package com.example.jhend.warehousesupplieslocator.Activities;

import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jhend.warehousesupplieslocator.R;
import com.example.jhend.warehousesupplieslocator.database.DatabaseManager;
import com.example.jhend.warehousesupplieslocator.model.Item;
import com.example.jhend.warehousesupplieslocator.utility.ActivityUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class ItemSearch extends ListActivity {
    DatabaseManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search);
        handleIntent(getIntent());
// Parallel array of which template objects to bind to those columns.

        databaseManager = ActivityUtils.getDatabaseManagerForActivity(getApplicationContext());
        addItemDatabase("Carflex 1 in", "C00", "Flexible rubber pipe");
        addItemDatabase("Carflex 2 in", "C00", "Flexible rubber pipe");
        addItemDatabase("Carflex 1.5 in", "C00", "Flexible rubber pipe");
        addItemDatabase("Carflex .75 in", "C00", "Flexible rubber pipe");

        loadSearchView();

    }

    @Override
    public boolean onSearchRequested() {
        return super.onSearchRequested();
    }


    private void handleIntent(Intent intent) {
        Log.d("intent", intent.getAction().toString());
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
        }
    }

    void loadSearchView(){
        SearchView searchView =
                (SearchView) findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("query", query);
                handleQuery(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }
    void handleQuery(String query){
        Log.d("Action", "Handling Query");
        ArrayList<String> results = databaseManager.findItemsNameList(query);
        if(results == null){
            return;
        }
        ArrayAdapter<String> resultsAdapater = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                results);
        setListAdapter(resultsAdapater);
        setViewListeners();
    }

    void addItemDatabase(final String itemName, final String cellName, final String Description){
        new AsyncTask<Void, Void, Void>(){
            protected Void doInBackground(Void... voids) {
                databaseManager.addItemToDatabase(new Item(itemName, cellName, Description));
                return null;
            }
        }.execute();

    }
    void setViewListeners(){
        final ListView listView = getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String itemName = listView.getItemAtPosition(i).toString();
                Log.d("item click", itemName);
                Intent intent = new Intent(getApplicationContext(), ItemLocator.class);
                intent.putExtra("item_name", itemName);
                startActivity(intent);
            }
        });
    }

}
