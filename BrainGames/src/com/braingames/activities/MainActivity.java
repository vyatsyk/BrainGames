/**
 * This package contain a main class for build "game view"
 */
package com.braingames.activities;

import com.braingames.R;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

/**
 * 
 * @author 
 * @version 0.1
 */
public class MainActivity extends Activity {

	/**
	 * Tag for logging
	 */
	public final String LOG_TAG = "MAIN_ACTIVITY";
	
	/**
	 * Create main activity
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "View is set.");
    }

    /**
     * Create menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
