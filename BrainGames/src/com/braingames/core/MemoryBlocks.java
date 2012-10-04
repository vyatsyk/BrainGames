/**
 * Core of super project BrainGames
 */
package com.braingames.core;

import java.util.Random;

//import android.util.Log;

/**
 * This class describes the basic principles of the game. 
 * The player has to memorize and retrieve a matrix.
 * 
 * @author Bogdan Rutylo
 * @version 0.1
 */
public class MemoryBlocks {
	
	/**
	 * Logging tag.
	 */
	public static final String LOG_TAG = "MemoryBlocks";
	
	/**
	 * This is a field where the player must find the right marked blocks.
	 */
	private boolean [][] gameField;
	
	public static final int MAX_SIZE = 6; //Example 6x6 
	public static final int MIN_SIZE = 3; //Example 3x3
	//TODO: We need to create playing fields that are not squares?
	
	/**
	 * This constructor needs call(once) when game is run. 
	 */
	public MemoryBlocks() {
		
		//first allocation
		if(!allocateMemoryForField(MIN_SIZE)) {
			//Log.d(LOG_TAG, "Method allocateMemory() return - false");
		}
		
		if(!fillGameField()) {
			//Log.d(LOG_TAG, "Method fillGameField() return - false");
		}
		
	}

	/**
	 * Create new array
	 * 
	 * @param size of new array
	 * @return true, if operation is successful
	 */
	private boolean allocateMemoryForField(int size) {
		
		if(size < MIN_SIZE || size > MAX_SIZE) {
			return false;
		}
		
		try {
			gameField = new boolean [size][size];
		} catch (Exception e) {
			// TODO: handle exception
			//Log.d(LOG_TAG, "Some trouble with memory allocation");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Fills a field. Where the element of array is equals - "true",
	 *  this need to memorize..
	 *  
	 * @return true, if operation is successful
	 */
	private boolean fillGameField() {
		
		if(gameField == null) {
			return false;
		}
		
		//Indexes of elements which will be set in "true"
		int [] indexes = new int [gameField.length];
		for(int i = 0; i < indexes.length; i++) {
			indexes[i] = -1;
		}
		int tmp;
		
		//Random select the indexes (not work!!!) Тут я хотів згенерити унікальні
		//індекси по яких би постави тру, але не генрю випадково цілий масив зарзу, бо тоді невідомо скільки тих тру елемнтів буде..а треба щоб спочаку мало.. а потім багато. в залежності від розміру.
		/*
		Random random = new Random();
		for(int i = 0; i < gameField.length; i++) {
			tmp = random.nextInt(gameField.length);
			for(int j = 0; j < indexes.length; j++) {
				if(tmp == indexes[j]) {
					i--;
					break;
				}
				indexes[i] = tmp;
				//break;
			}
		}
		*/
		
		//filling
		for(int i = 0; i < gameField.length; i++) {
			for(int j = 0; j < gameField.length; j++) {
				gameField[i][j] = false;
			}
		}
		
		return true;
	}
	
	public boolean[][] getGameFiled() {
		if(gameField != null) {
			return gameField;
		} 
		return null;
	}
	
	/**
	 * Method for TESTING
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MemoryBlocks mb = new MemoryBlocks();
		boolean [][] gf = mb.getGameFiled();
		
		for(int i = 0; i < gf.length; i++) {
			for(int j = 0; j < gf.length; j++) {
				System.out.print(gf[i][j] + " ");
			}
			System.out.println();
		}
	}

}
