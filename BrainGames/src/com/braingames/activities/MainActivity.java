/**
 * This package contain a main class for build "game view"
 */
package com.braingames.activities;

import com.braingames.R;
import com.braingames.core.MemoryBlocks;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * 
 * @author 
 * @version 0.1
 */
public class MainActivity extends Activity {

	/**
	 * Tag for logging
	 */
	public static final String LOG_TAG = "MAIN_ACTIVITY";
	//variable for amount of tiles in square
	private int fieldCount = 3;
	private LinearLayout rlMain = null;
	private Button[][] fieldArray = null;
	/**
	 * Create main activity
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rlMain = (LinearLayout)findViewById(R.id.rlMainField);
        createField();
    }

    private void createField() {
    	fieldArray = new Button[fieldCount][fieldCount];
    	for (int i = 0; i < fieldCount; i++){
    		LinearLayout llLine = new LinearLayout(this);
    		llLine.setOrientation(LinearLayout.HORIZONTAL);
    		rlMain.addView(llLine);
    		for (int j = 0; j < fieldCount; j++){
    			fieldArray[i][j] = new Button(this);
    			fieldArray[i][j].setWidth(50);
    			llLine.addView(fieldArray[i][j]);
    		}
    	}
	}
    
    private void setChecked(Button button){
    	button.setPressed(true);
    }
    
    public void startClick(View v) {
    	v.setVisibility(View.INVISIBLE);
    	new Thread(new MemoryBlocks(fieldCount, fieldArray, this)).start();
	}
    
//	@Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }

    
}
