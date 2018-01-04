package com.example.jhend.warehousesupplieslocator.utility;

import android.content.Context;
import android.graphics.Interpolator;
import android.os.AsyncTask;
import android.service.media.MediaBrowserService;
import android.support.v4.media.MediaBrowserServiceCompat;

import com.example.jhend.warehousesupplieslocator.database.DatabaseManager;

import java.util.concurrent.ExecutionException;

/**
 * Created by jhend on 1/4/2018.
 */

public class ActivityUtils {
    /**
     * DatabaseManager has to be intialized on a thread other than main which is what this method
     * does
     * @param context
     * @return the instance
     */
    public static DatabaseManager getDatabaseManagerForActivity(final Context context) {
        DatabaseManager instance = null;
        try {
            instance = new AsyncTask<Void, Void, DatabaseManager>() {
                protected DatabaseManager doInBackground(Void... voids) {
                    DatabaseManager instance = DatabaseManager.getInstance();
                    instance.loadDatabaseManager(context);
                    return instance;
                }
                protected void onPostExecute(DatabaseManager result){

                }
            }.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return instance;
    }
}
