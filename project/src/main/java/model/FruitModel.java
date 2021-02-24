package model;

import java.util.Random;

import controller.SnakeApp;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Coordinate;

public class FruitModel {
	
	private Color fruitColor = Color.RED;
	private int randomNumberX;
	private int randomNumberY;
	
	/*
	public FruitModel(Color fruitColor) {
		this.fruitColor = fruitColor;
	}*/
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
	/*
	private void setFruitPosition() {
		createRandomPositionX();
		createRandomPositionY();
		Coordinate fruit = new Coordinate(createRandomPositionX(), createRandomPositionY());
	}*/
	/*
	private Coordinate getFruitPosition() {
		Coordinate fruit = new Coordinate(createRandomPositionX(), createRandomPositionY());
		return fruit;
	}*/
	
	
	public static void main(String[] args) {
		Coordinate fruit = new Coordinate(createRandomPositionX(), createRandomPositionY());
	
		System.out.println(fruit.getX());
		System.out.println(fruit.getY());
		
		//System.out.println(createRandomPositionX());
		//System.out.println(createRandomPositionY());
	}
	/*
	private int createRandomPositionY() {
		Random randomFruit = new Random();
		int randomNumber = randomFruit.nextInt(game.getHeight()); 
		return randomNumber;
	}*/
	
	
	/*
	private void setFruitPosition() {
		// TODO: Check that body is not longer than board width;
		this.snakeBody.add(new Coordinate(5,0));
		}
	*/
}
