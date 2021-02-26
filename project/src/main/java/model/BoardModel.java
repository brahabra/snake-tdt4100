package model;

import java.util.ArrayList;

import controller.SnakeController;

public class BoardModel {
	
	private int height;
	private int width;
	private int pixelSize;
	private Coordinate[][] board;
	private ArrayList<Coordinate> snake;
	private boolean isGameOver = false;
	private FruitModel fruit; 
	
	
	public BoardModel(int width, int height, int pixelSize){
		
		this.height = height;
		this.width = width;
		this.pixelSize = pixelSize;
	
	

		/*this.board = new Coordinate[height][width];
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				board[y][x] = new Coordinate(x, y);
			}
		
		}*/
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
	
	public void setFruit(FruitModel fruit) {
		this.fruit = fruit;
	}
	/*
	public boolean inBounds(int x, int y) {
		return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
	}
	
	public Coordinate getCoordinate(int x, int y) {
		if (!inBounds(x, y)) {
			throw new IllegalArgumentException("Coordinates out of bounds");
		}
		return this.board[y][x];
	}
	
	public void addSnakeToTiles(Coordinate snakeHead, Coordinate snakeBody) {
		if (snake != null) {
            throw new IllegalStateException("Snake already created");
        }
		snake = new ArrayList<Coordinate>();
		
		snakeHead.setSnakeHead();
		snakeBody.setSnakeBody();
		snake.add(snakeHead);
		snake.add(snakeBody);
	}
	
	public void moveUp() {
        move(0, -1);
    }

    public void moveDown() {
        move(0, 1);
    }

    public void moveLeft() {
        move(-1, 0);
    }

    public void moveRight() {
        move(1, 0);
    }
    
    private boolean canMoveTo(int dx, int dy) {
		
		int targetX = snake.get(0).getX() + dx;
		int targetY = snake.get(0).getY() + dy;
		
		if(!inBounds(targetX, targetY)) {
			return false;
		}
		
		Coordinate targetTile = getCoordinate(targetX, targetY);
		boolean tileIsSnakeTail = (targetTile == snake.get(snake.size()-1));
		
		return !targetTile.hasCollision() || tileIsSnakeTail;
	}


	private void move(int dx, int dy) {
		if(snake == null) {
    		throw new IllegalStateException("Not a valid game state for move");
		}
		if(!canMoveTo(dx, dy)) {
			throw new IllegalArgumentException("Not a valid move");
		}

		int targetX = snake.get(0).getX() + dx;
		int targetY = snake.get(0).getY() + dy;
		Coordinate targetTile = getCoordinate(targetX, targetY);
	}*/
	
	public boolean getIsGameOver() {
		return this.isGameOver;
    }
	
	public void setGameOver() {
		this.isGameOver = true; 
	}
	
	
	public boolean isGameOver(int dx, int dy, int x, int y) {
		return !canMoveTo(dx, dy) && !inBounds(x, y);
	}
	
}
