package model;

import java.util.ArrayList;

import utils.Dir;
import javafx.scene.paint.Color;

public class SnakeModel {
	
	private int initSize;
	private int speed;
	private Color snakeColor = Color.GREENYELLOW;
	private Dir direction = Dir.right;
	public ArrayList<Coordinate> snakeBody = new ArrayList<>();
	
	public SnakeModel(int initSize, int speed) {
		this.initSize = initSize;
		this.speed = speed;
		setSnakeBody(initSize);
	}
	
	public int getInitSize() {
		return this.initSize;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public void setIncreasedSpeed() {
		this.speed += 1;
	}
	
	public Dir getDirection() {
		return this.direction;
	}

	public void setDirection(Dir direction) {
		this.direction = direction;
	}
	
	public ArrayList<Coordinate> getSnake() {
		return this.snakeBody;
	}
	
	public Color getSnakeColor() {
		return this.snakeColor;
	}
	
	public void setSnakeColor(Color snakeColor) {
		this.snakeColor = snakeColor;
	}
	
	private void setSnakeBody(int initSize) {
		for (int i = 0; i < initSize; i++) {
			this.snakeBody.add(new Coordinate(initSize - 1,0));
			}
		}
	}			