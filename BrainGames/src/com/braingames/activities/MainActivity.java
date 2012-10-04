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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * 
 * @author 
 * @version 0.1
 */
public class MainActivity extends Activity implements Runnable, OnClickListener, OnTouchListener{

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
    			fieldButtonArray[i][j].setClickable(false);
    			fieldButtonArray[i][j].setOnClickListener(this);
    			llLine.addView(fieldButtonArray[i][j]);
    		}
    	}
	}
    
    private void deleteField(){
    	rlMain.removeAllViews();
    }
    
    public void startClick(View v) {
    	bStart = (Button)v;
    	blockCounter = 0;
    	fieldBoolArray = blocks.getGameFiled();
    	deleteField();
        createField();
    	v.setVisibility(View.INVISIBLE);
    	new Thread(this).start();
	}

	public void onClick(View v) {
		Log.d(LOG_TAG, "onClick");
		for (int i = 0; i < fieldCount[0]; i++){
			for (int j = 0; j < fieldCount[1]; j++) {
				if ((Button)v == fieldButtonArray[i][j]) {
					if (!fieldButtonArray[i][j].isPressed())
						Log.d(LOG_TAG, "Button not pressed");
						if (!fieldBoolArray[i][j]) {
							error(i, j);
							return;
						} 
				}
			}
		}
	}
	
	private void finishGame() {
		Log.d(LOG_TAG, "finishGame()");
		blockCounter = 0;
		blocks.increaseGameField();
		bStart.setText("Next");
		bStart.setVisibility(View.VISIBLE);
	}

	private void error(int i, int j) {
		//blocks.decreaseGameField();
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
							fieldButtonArray[i][j].setOnTouchListener(MainActivity.this);
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
		runOnUiThread(new Runnable() {
			
			public void run() {
				for (Button i[]: fieldButtonArray){
					for (Button j : i){
						j.setPressed(false);
					}
				}
			}
		});
	}

	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP){
			v.setPressed(true);
			blockCounter++;
			Log.d(LOG_TAG, blockCounter + " " + blocks.getRandomCounts());
			if (blockCounter == blocks.getRandomCounts()) {
				finishGame();
			}
			return true;
		}
		else
			return false;
	}
    
}
