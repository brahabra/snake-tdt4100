package model;

import java.util.ArrayList;

import controller.SnakeController;
import controller.StartMenuController;

public class BoardModel {
	
	private int height;
	private int width;
	private int pixelSize;
	private Coordinate[][] board;
	private ArrayList<Coordinate> snake;
	private boolean isGameOver = false;
	private FruitModel fruit; 
	private int fruitScore = 0;
	
	
	public BoardModel(int width, int height, int pixelSize){
		
		this.height = height;
		this.width = width;
		this.pixelSize = pixelSize;
		this.fruit = new FruitModel();
	}
	public void increaseFruitScore() {
		this.fruitScore += 1;
	}
	
	public int getFruitScore() {
		return fruitScore;
	}
	
	public int getPixelSize() {
		return this.pixelSize;
	}
	
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	public FruitModel getFruit() {
		return fruit;
	}
	
	public void setFruit() {
		this.fruit = new FruitModel();
	}
	
	public boolean getIsGameOver() {
		return this.isGameOver;
    }
	
	public void setGameOver() {
		this.isGameOver = true; 
	}
}
