/**
 * This package contain a main class for build "game view"
 */
package com.braingames.activities;

import java.util.concurrent.TimeUnit;

import com.braingames.R;
import com.braingames.core.MemoryBlocks;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * 
 * @author 
 * @version 0.1
 */
public class MainActivity extends Activity implements Runnable, OnClickListener{

	/**
	 * Tag for logging
	 */
	public static final String LOG_TAG = "MAIN_ACTIVITY";
	//variable for amount of tiles in square
	private int fieldCount[] = new int[2];
	private int blockCounter = 0;
	private MemoryBlocks blocks = null;
	private LinearLayout rlMain = null;
	private Button[][] fieldButtonArray = null;
	private Button bStart = null;
	private boolean [][] fieldBoolArray = null;
	/**
	 * Create main activity
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rlMain = (LinearLayout)findViewById(R.id.rlMainField);
        blocks = new MemoryBlocks();
        createField();
    }

    private void createField() {
    	fieldCount[0] = fieldBoolArray.length;
    	fieldCount[1] = fieldBoolArray[0].length;
    	fieldButtonArray = new Button[fieldCount[0]][fieldCount[1]];
    	for (int i = 0; i < fieldCount[0]; i++){
    		LinearLayout llLine = new LinearLayout(this);
    		llLine.setOrientation(LinearLayout.HORIZONTAL);
    		rlMain.addView(llLine);
    		for (int j = 0; j < fieldCount[1]; j++){
    			fieldButtonArray[i][j] = new Button(this);
    			fieldButtonArray[i][j].setWidth(50);
    			llLine.addView(fieldButtonArray[i][j]);
    		}
    	}
	}
    
    
    
    public void startClick(View v) {
    	bStart = (Button)v;
    	v.setVisibility(View.INVISIBLE);
    	new Thread(this).start();
	}

	public void onClick(View v) {
		for (int i = 0; i < fieldCount[0]; i++){
			for (int j = 0; j < fieldCount[1]; j++){
				if (v == fieldButtonArray[i][j]){
					if (!fieldBoolArray[i][j]){
						error(i, j);
					} else{
						blockCounter++;
						pressButton(fieldButtonArray[i][j]);
						if (blockCounter == blocks.getCounter()){
							finishGame();
						}
					}
				}
			}
		}
	}
	
	private void finishGame() {
		bStart.setText("Next");
		bStart.setVisibility(View.VISIBLE);
	}

	private void pressButton(Button button) {
		button.setPressed(true);
	}

	private void error(int i, int j) {
		showField();
		showError(i, j);
	}

	private void showError(final int i, final int j) {
		runOnUiThread(new Runnable() {
			
			public void run() {
				fieldButtonArray[i][j].setBackgroundColor(Color.RED);
				bStart.setText("Next");
				bStart.setVisibility(View.VISIBLE);
			}
		});
	}

	private void showField(){
		runOnUiThread(new Runnable() {
			
			public void run() {
				for (int i = 0; i < fieldCount[0]; i++){
					for (int j = 0; j < fieldCount[1]; j++){
						if (fieldBoolArray[i][j]){
							fieldButtonArray[i][j].setPressed(true);
						}
					}
				}				
			}
		});
	}

	public void run() {
		showField();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			Log.d(LOG_TAG, "Interrupt Exception from sleep.");
		}
		hideField();
	}

	private void hideField() {
		for (Button i[]: fieldButtonArray){
			for (Button j : i){
				j.setPressed(false);
			}
		}
	}
    
}
