/**
 * Core of super project BrainGames
 */
package com.braingames.core;

import java.util.Random;

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
	
	private int width = 3;
	private int heigth = 3;
	private int randomCounts = 3;
	
	
	/**
	 * This constructor needs call(once) when game is run. 
	 */
	public MemoryBlocks() {
		
		//first allocation
		if(!allocateMemoryForField(heigth, width)) {
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
	private boolean allocateMemoryForField(int heigth_size, int width_size) {
		
		if(heigth_size < MIN_SIZE || heigth_size > MAX_SIZE 
								  || width_size < MIN_SIZE
								  || width_size > MAX_SIZE) {
			return false;
		}
		
		try {
			gameField = new boolean [heigth_size][width_size];
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
		
		int true_counts = 0;
		//Random select the indexes 
		Random random = new Random();
		for(int i = 0; i < heigth; i++) {
			for(int j = 0; j < width; j++) {
				gameField[i][j] = random.nextBoolean();
				if(gameField[i][j] == true) {
					true_counts++;
				}
				if(true_counts == randomCounts) {
					return true;
				}
			}
		}
				
		return true;
	}
	
	/**
	 * 
	 * @return false if increase aborted
	 */
	public boolean increaseGameField() {
		
		if(heigth == MAX_SIZE && width == MAX_SIZE) {
			return false;
		}
		try{
			if(heigth == width) {
				width++;
			} else {
				heigth++;
			}
			randomCounts++;
			allocateMemoryForField(heigth, width);
			fillGameField();
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		return true;
	}
	
	/**
	 * 
	 * @return false if decrease operation is aborted
	 */
	public boolean decreaseGameField() {
		
		if(heigth == MIN_SIZE && width == MIN_SIZE) {
			return false;
		}
		try{
			if(heigth == width) {
				width--;
			} else {
				heigth--;
			}
			randomCounts--;
			allocateMemoryForField(heigth, width);
			fillGameField();
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		return true;
	}
	
	public boolean[][] getGameFiled() {
		if(gameField != null) {
			return gameField;
		} 
		return null;
	}
	
	public int getRandomCounts() {
	 
		return randomCounts;
	}
	
	/**
	 * Method for TESTING
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MemoryBlocks mb = new MemoryBlocks();
		boolean [][] gf = mb.getGameFiled();
		
		print(gf);
		
		mb.increaseGameField();
		gf = mb.getGameFiled();
		System.out.println();
		print(gf);
		
		mb.increaseGameField();
		gf = mb.getGameFiled();
		System.out.println();
		print(gf);
		
		mb.increaseGameField();
		gf = mb.getGameFiled();
		System.out.println();
		print(gf);
		
		mb.increaseGameField();
		gf = mb.getGameFiled();
		System.out.println();
		print(gf);
		
		mb.increaseGameField();
		gf = mb.getGameFiled();
		System.out.println();
		print(gf);
		
		mb.increaseGameField();
		gf = mb.getGameFiled();
		System.out.println();
		print(gf);
		
		//last - return false
		mb.increaseGameField();
		gf = mb.getGameFiled();
		System.out.println();
		print(gf);
		
	}
	
	/*
	 * for print test results
	 */
	public static void print(boolean [][] gf) {
		
		for(int i = 0; i < gf.length; i++) {
			for(int j = 0; j < gf[0].length; j++) {
				System.out.print(gf[i][j] + " ");
			}
			System.out.println();
		}
	}

}
