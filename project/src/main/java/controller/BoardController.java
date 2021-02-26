package controller;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.BoardModel;
import model.Coordinate;
import model.FruitModel;
import model.SnakeModel;
import controller.SnakeApp;

import java.awt.event.KeyAdapter;
import java.util.Random;


public class BoardController  {
	
	private BoardModel game;
	
	@FXML
	private Pane board;
	
	/*
	public void initialize() {
		setInitialGameState();
		createBoard();
		drawBoard();
		
	}*/
	
	public BoardController(BoardModel model) {
	        this.game = model;
	   }
	/*
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
	*/
	public void fillFood(GraphicsContext graphicsContext) {
		int pxSize = this.game.getPixelSize();
		//FruitModel fruit = this.game.getFruit();
		graphicsContext.setFill(Color.BLUE);
	//	graphicsContext.fillRect(fruit.getPositionX() * pxSize,fruit.getPositionY() * pxSize, pxSize - 1, pxSize - 1);
		graphicsContext.fillRect(5 * pxSize,5 * pxSize, pxSize - 1, pxSize - 1);
		
		
	}
	
	
	public void start(Scene scene, GraphicsContext graphicsContext, SnakeModel snake ) {
			
			fillFood(graphicsContext);
			SnakeController snakeController = new SnakeController(snake);
			
			//FruitModel newFruit = new FruitModel();
			//snakeController.move();
			//snakeController.fillSnake(graphicsContext, this.game);
			
			
	        new AnimationTimer() {
	            long tick = 0;
	            
	            @Override
	            public void handle(long now) {
	                
	                if (getIsGameOver()) {
	                    drawGameOver(graphicsContext);
	                    this.stop(); // game ends
	                    return;
	                }
	                
	                if (tick == 0 || now - tick > 1000000000 / snake.getSpeed()) { // to handle the speed of the game
	                    tick = now;
	                  
	                    snakeController.move();
	                    snakeController.fillSnake(graphicsContext, game);
	                    fillFood(graphicsContext);
	                   // boardController.fillFood(graphicsContext);
	                    
	                    if (snakeController.snakeCrashed(game)) {
	                        setGameOver();
	                    }
	                }
	                
	            }
	            
	        }.start();
	  
	        
	       // setBoardKeyEventListeners(scene, snake);
	       
	    }
	    
	 private void drawGameOver(GraphicsContext graphicsContext) {
	        graphicsContext.setFont(new Font("", 50));
	        graphicsContext.setFill(Color.RED);
	        graphicsContext.fillText("Game over", 125, 250);
	 }
	 
	   
	    
    private void setGameOver() {
        this.game.setGameOver();
    }
    
    private boolean getIsGameOver() {
        return this.game.getIsGameOver();
    }

	/*
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
	*/
	/*
	private void setInitialGameState() {
		game = new BoardModel(20, 20);
		game.getCoordinate(createRandomPositionX() - 1, createRandomPositionY() - 1).setFruit();	
		//game.getCoordinate(9, 9).setSnake();
		game.addSnakeToTiles(game.getCoordinate(8, 9), game.getCoordinate(8, 8));
		
	}*/
	/*
	public String setCoordinateColor(Coordinate coordinate) {		
		if (coordinate.getType() == 1) {
			return "#e5303a";
		}
		else if(coordinate.getType() == 2) {
			return "#2ec955";
		}
		else if(coordinate.getType() == 3) {
			return "#1e8a39";
		}
		
		else {
			return "#1c0000";
		}
	}*/
	
	@FXML
	private void movePosition() {
		
		System.out.println("TEST");
		
	}

/*
	private void drawBoard() {
		for (int y = 0; y < game.getHeight(); y++) {
			for (int x = 0; x < game.getWidth(); x++) {
				board.getChildren().get(y*game.getWidth() + x).setStyle("-fx-background-color: " + setCoordinateColor(game.getCoordinate(x, y)) + ";");
			}
		}
	}*/
}