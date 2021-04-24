package model;

import controller.StartMenuController;

public class BoardModel {
	
	private int height;
	private int width;
	private int pixelSize;
	private boolean isGameOver = false;
	private FruitModel fruit; 
	private int fruitScore = 0;

	public BoardModel(int width, int height, int pixelSize){
		
		this.height = height;
		this.width = width;
		this.pixelSize = pixelSize;
		this.fruit = new FruitModel();
	}
	
	public void resetFruitScore() {
		fruitScore = 0;
	}
	
	public void increaseFruitScore() {
		fruitScore += 1;
	}
	
	public  int getFruitScore() {
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
	
	//Lagd kun for å få testet: 
	
	public void setFruitScore(int fruitScore) {
		this.fruitScore = fruitScore;
	}
	
	public void setUsername(String username) {
		StartMenuController.username = username;
	}
	
	public String getUsername() {
		return StartMenuController.getUsername();
	}
}
