package model;

public class Coordinate {
	  
	private int x;
	private int y;
	private int type = 0;

	public Coordinate(int x, int y) {
	       this.x = x;
	       this.y = y;
	  }
	/*
	public void setAir() {
		type = 0;
	}*/
	
	public void setFruit() {
		type = 1;
	}
	
	public void setSnakeBody() {
		type = 2;
	}
	
	public void setSnakeHead() {
		type = 3;
	}
	
	public int getType() {
		return type;
	}
	
	public void setX(int x) {
		this.x = x; 
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		 return x;
	}

	 public int getY() {
		 return y;
	}
	 
	public boolean hasCollision() {
	     return getType() == 2;
	}


}
