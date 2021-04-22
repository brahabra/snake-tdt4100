package model;

import java.util.concurrent.ThreadLocalRandom;
import controller.SnakeApp;

public class FruitModel {
	private int x;
	private int y;
	
	public FruitModel() {
		this.x = generateRandomPositionX();
		this.y = generateRandomPositionY();
	}
	
	public int generateRandomPositionX() {
		int randomX = ThreadLocalRandom.current().nextInt(1, SnakeApp.BOARD_HEIGHT - 1);
		return randomX;
	}
	
	public int generateRandomPositionY() {
		int randomY = ThreadLocalRandom.current().nextInt(1, (SnakeApp.BOARD_HEIGHT - 1) - SnakeApp.SCOREBOARD_BANNER_HEIGHT / SnakeApp.PIXEL_SIZE);
		return randomY;
	}
	
	public void setRandomPositionX() {
		this.x = generateRandomPositionX();
	}

	public void setRandomPositionY() {
		this.y = generateRandomPositionY();
	}
	
	public int getPositionX() {
		return x;
	}
	
	public int getPositionY() {
		return y;
	}
	
	// Lagd kun for å få testet: 
	public void setPositionX(int x) {
		this.x = x;
	}
	
	public void setPositionY(int y) {
		this.y = y;
	}
}
