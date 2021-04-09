package controller;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class BoardController  {
	
	public static BoardModel game;
	private Coordinate[][] board;
	
	//private StartMenuController username;
	 
	public BoardController(BoardModel model) {
	        game = model;
	   }
	
	public Coordinate getFruit(int x, int y) {
		return this.board[y][x];
	}
	
	public BoardModel getBoard() {
		return game;
	}
	
	public void fillFood(GraphicsContext graphicsContext, SnakeModel snakeModel) {
		int pxSize = game.getPixelSize();
		FruitModel fruit = game.getFruit();
		
		while(isFruitInSnake(snakeModel, fruit) != false) {
			System.out.println(" ****** TEST! OBS OBS OBS. Frukten spawnet i slangen, men ble flyttet automatisk. *******");
			fruit.setRandomPositionX();
			fruit.setRandomPositionY();
		
		}
		
		graphicsContext.setFill(Color.RED);
		graphicsContext.fillRect(fruit.getPositionX() * pxSize,fruit.getPositionY() * pxSize, pxSize - 1, pxSize - 1);
	}
	
	private boolean isFruitInSnake(SnakeModel snakeModel, FruitModel fruit) {
		
		ArrayList<Coordinate> snake = snakeModel.getSnake();
		
		for (Coordinate coordinate : snake) {
			if(coordinate.getX() == fruit.getPositionX() && coordinate.getY() == fruit.getPositionY()) {
				return true;
			}
		}
		return false;
		
	}
	
	public void startSnake(Scene scene, GraphicsContext graphicsContext, SnakeModel snake) {
		
			fillFood(graphicsContext, snake);
			SnakeController snakeController = new SnakeController(snake);
			BoardController boardController = this;
			
			
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
	                    snakeController.eatFruit(boardController);
	                    snakeController.fillSnake(graphicsContext, game);
	                    viewScore(graphicsContext);
	                    fillFood(graphicsContext, snake);
	                    viewUsername(graphicsContext);
	                   
	                    //boardController.fillFood(graphicsContext);
	                    
	                    if (snakeController.snakeCrashed(game)) {
	                        setGameOver();
	                        playGameOverSound("./gameOverSound.wav");
	                        FileHandler fh = new FileHandler();
	                        fh.writeScoreToFile("scorefile.txt");
	                    }
	                }
	                
	            }
	            
	        }.start();
	    }
//	private void writeScoreToFile(String filename) {
//		
//		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
//	    Date date = new Date();  
//
//		 
//		try {
//			//String filename = "Scoretest.txt";
//			FileWriter fw = new FileWriter(filename, true);
////			fw.write("Score: " + game.getFruitScore() + " || Date: " + formatter.format(date) + " || Username: " + StartMenuController.getUsername() + "\n");
//			fw.write(game.getFruitScore() + ";" + formatter.format(date) + ";" + StartMenuController.getUsername() + "\n");
//			fw.close();
//		}
//		catch(IOException ioe) 
//		{
//			System.err.println("IOException " + ioe.getMessage());
//		}
//	}
//	
	public void playEatingSound(String soundFile) {
	    try {
	    	File f = new File("./" + soundFile);
		    AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());  
		    Clip clip = AudioSystem.getClip();
		    clip.open(audioIn);
		    clip.start();
	    }
	    catch(Exception e) {
	    	System.out.println("Could not open the file: " + soundFile);
	    }
	    
	}
	
	public void playGameOverSound(String soundFile) {
	    try {
	    	File f = new File("./" + soundFile);
		    AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());  
		    Clip clip = AudioSystem.getClip();
		    clip.open(audioIn);
		    clip.start();
	    }
	    catch(Exception e) {
	    	System.out.println("Could not open the file: " + soundFile);
	    }
	    
	}

	 private void drawGameOver(GraphicsContext graphicsContext) {
	        graphicsContext.setFont(new Font("Courier New", 50));
	        graphicsContext.setFill(Color.RED);
	        graphicsContext.fillText("GAME OVER", 125, 150);
	       
	        
	        graphicsContext.setFont(new Font("Courier New", 20));
	        graphicsContext.setFill(Color.RED);
	        graphicsContext.fillText("Hit 'SPACEBAR' to play again!", 100, 250);
	        
	        graphicsContext.setFont(new Font("Courier New", 20));
	        graphicsContext.setFill(Color.RED);
	        graphicsContext.fillText("Hit 'M' to go back to the menu", 100, 270);
	        
	        graphicsContext.setFont(new Font("Courier New", 20));
	        graphicsContext.setFill(Color.RED);
	        graphicsContext.fillText("Hit 'Q' to quit Snake", 100, 290);
	             
	 }
	 
	 private void viewScore(GraphicsContext graphicsContext) {
		 	String scoreText = String.format("Score: %s", game.getFruitScore());
		 	graphicsContext.setFont(new Font("Courier New", 15));
		 	graphicsContext.setFill(Color.BLACK);
		 	graphicsContext.fillText(scoreText, 20, 490);
	 }
	 
	 private void viewUsername(GraphicsContext graphicsContext) {
		 	String nameText = StartMenuController.getUsername();
		 	graphicsContext.setFont(new Font("Courier New", 15));
		 	graphicsContext.setFill(Color.BLACK);
		 	graphicsContext.fillText("Username: " + nameText, 150, 490);
	 }
	 
    private void setGameOver() {
        game.setGameOver();
    }
    
    public static boolean getIsGameOver() {
        return game.getIsGameOver();
    }
	
}