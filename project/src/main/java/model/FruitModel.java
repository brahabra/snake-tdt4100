package model;

import java.util.Random;

import controller.SnakeApp;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Coordinate;


public class FruitModel {
	private Color fruitColor = Color.RED;
	private int x;
	private int y;
	
	public FruitModel() {
		this.x = setPositionX();
		this.y = setPositionY();
	}
	
	public void setFruitColor(Color fruitColor) {
		this.fruitColor = fruitColor;
	}
	
	public Color getFruitColor() {
		return fruitColor;
	}
	
	private int setPositionX() {
		Random randomFruit = new Random();
		int x = randomFruit.nextInt(SnakeApp.BOARD_WIDTH); 
		//TODO Fikse at man ikke får kjipe frukter langs ramma, typ. 0 og 50
		return x;
	}
	
	private int setPositionY() {
		Random randomFruit = new Random();
		int y = randomFruit.nextInt(SnakeApp.BOARD_HEIGHT - SnakeApp.SCOREBOARD_BANNER_HEIGHT / SnakeApp.PIXEL_SIZE); 
		//TODO Fikse at man ikke får kjipe frukter langs ramma, typ. 0 og 50
		return y;
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
}
