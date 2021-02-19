package model;

import java.util.ArrayList;


public class BoardModel {
	private int[][] board = new int[20][20];
	private ArrayList<Coordinate> snake;
	

	
	public boolean outOfBounds(int x, int y) {
		return x >= 0 && x < 21 && y >= 0 && y < 21;
	}
	
	public Coordinate getCoordinate(int x, int y) {
		if (!outOfBounds(x, y)) {
			throw new IllegalArgumentException("Coordinates out of bounds");
		}
	}
	
	public void addSnakeToTiles(Coordinate snakeHead, Coordinate snakeBody) {
		if (snake != null) {
            throw new IllegalStateException("Snake already created");
        }
		snake = new ArrayList<Coordinate>();
		
		snakeHead.setSnake();
		snakeBody.setSnake();
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
		
		if(!outOfBounds(targetX, targetY)) {
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
	}
	
	
	
	public String setColor() {
			
		if (getType() == 1) {
			return "#e5303a";
		}
	}	
}
