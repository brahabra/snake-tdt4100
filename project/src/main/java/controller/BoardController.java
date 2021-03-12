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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class BoardController  {
	
	private BoardModel game;
	private Coordinate[][] board;
	
	public BoardController(BoardModel model) {
	        this.game = model;
	   }
	
	public Coordinate getFruit(int x, int y) {
		return this.board[y][x];
	}
	
	public BoardModel getBoard() {
		return this.game;
	}
	
	public void fillFood(GraphicsContext graphicsContext) {
		int pxSize = this.game.getPixelSize();
		FruitModel fruit = this.game.getFruit();
		graphicsContext.setFill(Color.RED);
		graphicsContext.fillRect(fruit.getPositionX() * pxSize,fruit.getPositionY() * pxSize, pxSize - 1, pxSize - 1);
	}
	
	public void startSnake(Scene scene, GraphicsContext graphicsContext, SnakeModel snake) {
			
			fillFood(graphicsContext);
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
	                    fillFood(graphicsContext);
	                   
	                    //boardController.fillFood(graphicsContext);
	                    
	                    if (snakeController.snakeCrashed(game)) {
	                        setGameOver();
	                        playGameOverSound("./gameOverSound2.wav");
	                        writeScoreToFile("scorefile.txt");
	                    }
	                }
	                
	            }
	            
	        }.start();
	    }
	private void writeScoreToFile(String filename) {
//		try {
//			PrintWriter writer = new PrintWriter(filename);
//			writer.println("Din score var: " + game.getFruitScore());
//		
//			writer.flush();
//			writer.close();
//		
//	}catch(FileNotFoundException e) {
//			e.printStackTrace();
//		}
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  

		 
		try {
			//String filename = "Scoretest.txt";
			FileWriter fw = new FileWriter(filename, true);
			fw.write("Din score var: " + game.getFruitScore() + ". Tidspunkt var " + formatter.format(date) + "\n");
			fw.close();
		}
		catch(IOException ioe) 
		{
			System.err.println("IOException " + ioe.getMessage());
		}
	}
	
	public void playEatingSound(String soundFile) {
	    try {
	    	File f = new File("./" + soundFile);
		    AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());  
		    Clip clip = AudioSystem.getClip();
		    clip.open(audioIn);
		    clip.start();
	    }
	    catch(Exception e) {
	    	System.out.println("Something went wrong!");
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
	    	System.out.println("Something went wrong!");
	    }
	    
	}
	
//	// TODO LESE FRA FIL
//	private void getScoresFromFile(String filename) throws FileNotFoundException{
//		
//	}
	
	    
	 private void drawGameOver(GraphicsContext graphicsContext) {
	        graphicsContext.setFont(new Font(50));
	        graphicsContext.setFill(Color.RED);
	        graphicsContext.fillText("Game over", 125, 250);
	 }
	 
	 private void viewScore(GraphicsContext graphicsContext) {
		 	String scoreText = String.format("Score: %s", game.getFruitScore());
		 	graphicsContext.setFont(new Font(15));
		 	graphicsContext.setFill(Color.BLACK);
		 	graphicsContext.fillText(scoreText, 20, 490);
	 }
	 
    private void setGameOver() {
        this.game.setGameOver();
    }
    
    private boolean getIsGameOver() {
        return this.game.getIsGameOver();
    }
	
	@FXML
	private void movePosition() {
		
		System.out.println("TEST");
		
	}
}