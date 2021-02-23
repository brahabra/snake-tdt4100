package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import model.BoardModel;
import model.Coordinate;
import java.util.Random;


public class BoardController  {
	
	private BoardModel game;
	
	@FXML
	private Pane board;
	
	
		
	public void initialize() {
		setInitialGameState();
		createBoard();
		drawBoard();
		
	}
	
	private void createBoard() {
	 for (int y = 0; y < game.getHeight(); y++) {
			for (int x = 0; x < game.getWidth(); x++) {
				Pane block = new Pane();
				block.setTranslateX(x*30);
				block.setTranslateY(y*30);
				block.setPrefWidth(30);
				block.setPrefHeight(30);
				block.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
				board.getChildren().add(block); 
		
			}
		}
	}
	
	private int createRandomPositionX() {
		Random randomFruit = new Random();
		int randomNumber = randomFruit.nextInt(game.getWidth()); 
		return randomNumber;
	}
	
	private int createRandomPositionY() {
		Random randomFruit = new Random();
		int randomNumber = randomFruit.nextInt(game.getHeight()); 
		return randomNumber;
	}
	
	private void setInitialGameState() {
		game = new BoardModel(20, 20);
		game.getCoordinate(createRandomPositionX() - 1, createRandomPositionY() - 1).setFruit();	
		game.getCoordinate(9, 9).setSnake();
		game.addSnakeToTiles(game.getCoordinate(7, 7), game.getCoordinate(8, 8));
		
	}
	
	public String setCoordinateColor(Coordinate coordinate) {		
		if (coordinate.getType() == 1) {
			return "#e5303a";
		}
		else if(coordinate.getType() == 2) {
			return "#2ec955";
		}
		else {
			return "#e6fcff";
		}
	}
	
	@FXML
	private void movePosition() {
		
		System.out.println("TEST");
		
	}
	
	
	
			
	private void drawBoard() {
		for (int y = 0; y < game.getHeight(); y++) {
			for (int x = 0; x < game.getWidth(); x++) {
				board.getChildren().get(y*game.getWidth() + x).setStyle("-fx-background-color: " + setCoordinateColor(game.getCoordinate(x, y)) + ";");
			}
		}
	}
}