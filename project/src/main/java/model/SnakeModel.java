package model;

import java.util.ArrayList;

import utils.Dir;
import javafx.scene.paint.Color;

public class SnakeModel {
	
	private int initSize;
	private int speed;
	private Color snakeColor = Color.GREENYELLOW;
	private Dir direction = Dir.right;
	private ArrayList<Coordinate> snakeBody = new ArrayList<>();
	
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
		this.speed += 3;
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
	
	private void setSnakeBody(int initSize) {
		// TODO: Check that body is not longer than board width;
		for (int i = 0; i < initSize; i++) {
			this.snakeBody.add(new Coordinate(initSize - 1,0));
			}
		}
	}
					
					
					
					
					
					