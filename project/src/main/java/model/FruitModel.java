package model;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import controller.SnakeApp;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Coordinate;

public class FruitModel {
	private Color fruitColor = Color.RED;
	private int x;
	private int y;
	public Coordinate fruitXY;
	
	
	public FruitModel() {
		this.x = generateRandomPositionX();
		this.y = generateRandomPositionY();
	}
	
	public void setFruitColor(Color fruitColor) {
		this.fruitColor = fruitColor;
	}
	
	public Color getFruitColor() {
		return fruitColor;
	}
	
	public int generateRandomPositionX() {
		int randomX = ThreadLocalRandom.current().nextInt(1, SnakeApp.BOARD_HEIGHT - 1);
//		fruitXY.setX(randomX);
		return randomX;
	}
	
	public int generateRandomPositionY() {
		int randomY = ThreadLocalRandom.current().nextInt(1, (SnakeApp.BOARD_HEIGHT - 1) - SnakeApp.SCOREBOARD_BANNER_HEIGHT / SnakeApp.PIXEL_SIZE);
//		fruitXY.setY(randomY);
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

	@Override
	public String toString() {
		return Integer.toString(x) + " " +  Integer.toString(y); 
	}
	
	public static void main(String[] args) {
		//FruitModel hei = new FruitModel();
		//hei.setPositionX();
		//System.out.println(fruitXY.getX());
	}
}
