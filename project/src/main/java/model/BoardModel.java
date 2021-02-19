package model;

import java.util.ArrayList;

import of6.lf.Tile;

public class BoardModel {
	private int[][] board = new int[20][20];
	private int type = 0;
	private ArrayList<Integer> snake;
	
	public void setAir() {
		type = 0;
	}
	
	public void setFruit() {
		type = 1;
	}
	
	public void setSnake() {
		type = 2;
	}
	
	public int getType() {
		return type;
	}
	
	public boolean outOfBounds(int x, int y) {
		return x >= 0 && x < 21 && y >= 0 && y < 21;
	}
	
	public void getTile(int x, int y) {
		if (!outOfBounds(x, y)) {
			throw new IllegalArgumentException("Coordinates out of bounds");
		}
	}
	
	public void addSnakeToTiles(int snakeHead, int snakeBody) {
		if (snake != null) {
            throw new IllegalStateException("Snake already created");
        }
		
		snake = new ArrayList<Integer>();
		
		snakeHead.setSnake();
		snakeBody.setSnake();
		snake.add(snakeHead);
		snake.add(snakeBody);
	}
	
	public String setColor() {
			
		if (getType() == 1) {
			return "#e5303a";
		}
	}	
}
