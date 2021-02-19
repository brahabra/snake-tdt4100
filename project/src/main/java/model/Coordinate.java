package model;

public class Coordinate {
	  
	private int x;
	private int y;
	private int type = 0;

	public Coordinate(int x, int y) {
	       this.x = x;
	       this.y = y;
	  }

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
	
	 public int getX() {
		 return x;
	}

	 public int getY() {
		 return y;
	}
	 
	public boolean hasCollision() {
	     return getType() == 2;
	}


	
	/*
	public int setColor() {
		
		if (getType()) {
			
		}
	}
	*/
	
	
	//Farge p� slangen
}
