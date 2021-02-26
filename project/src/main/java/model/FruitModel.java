package model;

import java.util.Random;

import controller.SnakeApp;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Coordinate;
import of6.lf.Tile;

public class FruitModel {
	
	private Color fruitColor = Color.RED;
	private int x;
	private int y;
	
	
	
	public FruitModel(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	
	/*
	public void fillFruit(GraphicsContext graphicsContext, BoardModel board) {
		int pxSize = board.getPixelSize();
		graphicsContext.fillRect(fruit.getX()*pxSize, getY()*pxSize, pxSize - 1, pxSize - 1);
	}
	*/
	public void setFruitColor(Color fruitColor) {
		this.fruitColor = fruitColor;
	}
	
	public Color getFruitColor() {
		return fruitColor;
	}
	
	public static int createRandomPositionX() {
		Random randomFruit = new Random();
		int randomNumberX = randomFruit.nextInt(SnakeApp.BOARD_WIDTH * SnakeApp.PIXEL_SIZE); 
		return randomNumberX;
	}

	public static int createRandomPositionY() {
		Random randomFruit = new Random();
		int randomNumberY = randomFruit.nextInt(SnakeApp.BOARD_WIDTH * SnakeApp.PIXEL_SIZE); 
		return randomNumberY;
	}
	
	public void setPositionX() {
		Random randomFruit = new Random();
		x = randomFruit.nextInt(SnakeApp.BOARD_WIDTH * SnakeApp.PIXEL_SIZE); 
	}
	
	public void setPositionY() {
		Random randomFruit = new Random();
		y = randomFruit.nextInt(SnakeApp.BOARD_WIDTH * SnakeApp.PIXEL_SIZE); 
		
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
	/*
	public static void main(String[] args) {
		FruitModel fruit = new FruitModel(3, 5);
		fruit.setPositionX();
		fruit.setPositionY();
		System.out.println(fruit);
	}*/
	/*
	public static void main(String[] args) {
		Coordinate fruit = new Coordinate(createRandomPositionX(), createRandomPositionY());
	
		System.out.println(fruit.getX());
		System.out.println(fruit.getY());
		
		//System.out.println(createRandomPositionX());
		//System.out.println(createRandomPositionY());
	}*/
	/*
	private int createRandomPositionY() {
		Random randomFruit = new Random();
		int randomNumber = randomFruit.nextInt(game.getHeight()); 
		return randomNumber;
	}*/
	
	
}
