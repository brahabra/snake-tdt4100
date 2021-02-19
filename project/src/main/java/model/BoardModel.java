package model;

public class BoardModel {
	int[][] brett = new int[20][20];
	private int type = 0;
	
	public void setAir() {
		type = 0;
	}
	
	public void setFruit() {
		type = 1;
	}
	
	public void setSnake() {
		type = 2;
	}
}
