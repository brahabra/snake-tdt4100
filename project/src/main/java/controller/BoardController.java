package controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.BoardModel;
import model.Coordinate;
import model.FruitModel;
import model.SnakeModel;
import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class BoardController  {
	
	private BoardModel game;
	private Coordinate[][] board;
	
	public BoardController(BoardModel model) {
	        game = model;
	}
	
	public Coordinate getFruit(int x, int y) {
		return this.board[y][x];
	}
	
	public BoardModel getBoard() {
		return game;
	}
	
	// Fyller brettet med en rød frukt som er tilfeldig satt.
	// Men før den settes ut, går den gjennom en sjekk isFruitInSnake() som kontrollerer
	// at frukten ikke skal settes i kroppen. 
	public void fillFood(GraphicsContext graphicsContext, SnakeModel snakeModel) {
		int pxSize = game.getPixelSize();
		FruitModel fruit = game.getFruit();
		
		while(isFruitInSnake(snakeModel, fruit) != false) {
			// Setter nye X og Y-posisjoner i håp om at de ikke er i slangen
			fruit.setRandomPositionX();
			fruit.setRandomPositionY();
		}
		graphicsContext.setFill(Color.RED);
		graphicsContext.fillRect(fruit.getPositionX() * pxSize,fruit.getPositionY() * pxSize, pxSize - 1, pxSize - 1);
	}
	
	public boolean isFruitInSnake(SnakeModel snakeModel, FruitModel fruit) {
		
		ArrayList<Coordinate> snake = snakeModel.getSnake();
		
		// Sjekker hvert koordinat i slangekroppen sin X og Y-verdi opp mpt frukten sin X og Y-verdi.
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
	                    this.stop(); // Stopper spillet
	                    return;
	                }
	                
	                if (tick == 0 || now - tick > 1000000000 / snake.getSpeed()) { // for å håndtere animasjonen brukes tick 
	                    tick = now;												// bestemmer hvor fort slangen skal bevege seg,
	                    														// jo høyere snake.getSpeed() er, desto flere move gjør
	                    														// slangen per sekund
	                    
	                    snakeController.move();
	                    snakeController.eatFruit(boardController);
	                    snakeController.fillSnake(graphicsContext, game);
	                    viewScore(graphicsContext, boardController);
	                    fillFood(graphicsContext, snake);
	                    viewUsername(graphicsContext);
	            
	                    if (snakeController.snakeCrashed(game)) {
	                    	
	                    	// Nyere tidspunkt settes høyere enn et gammelt. Så derfor større ELLER lik.
	                    	if(boardController.getBoard().getFruitScore() >= FileHandler.highscoreScore){
	                    		playNewHighScoreSound("../project/src/main/resources/newHighscoreSound.wav");
	                    		drawNewHighscoreText(graphicsContext);	                    		
	                    	}
	                    	
	                    	else {
	   	                        playGameOverSound("../project/src/main/resources/gameOverSound.wav");
	   	                        drawGameOver(graphicsContext);
	                    	}
	                    	
	                    	setGameOver();
	                    	FileHandler fh = new FileHandler();
	   	                    fh.writeScoreToFile(boardController, "scorefile.txt");
	                    	drawShortCutInformation(graphicsContext);
	                    	//boardController.getBoard().resetFruitScore();                 	
	                    }
	                }
	                
	            }
	            
	        }.start();
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
	    	System.out.println("Could not open the file '" + soundFile +  "'. Are you sure the 'eat fruit' file name is correct?");
	    	e.printStackTrace();
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
	    	System.out.println("Could not open the file '" + soundFile +  "'. Are you sure the the 'game over' file name is correct?");
	    	e.printStackTrace();
	    }
	    
	}
	
	public void playNewHighScoreSound(String soundFile) {
	    try {
	    	File f = new File("./" + soundFile);
		    AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());  
		    Clip clip = AudioSystem.getClip();
		    clip.open(audioIn);
		    clip.start();
	    }
	    catch(Exception e) {
	    	System.out.println("Could not open the file '" + soundFile +  "'. Are you sure the 'new highscore' file name is correct?");
	    }
	}

	public void drawGameOver(GraphicsContext graphicsContext) {
		 	
		 graphicsContext.setFont(new Font("Courier New", 50));
		 graphicsContext.setFill(Color.RED);
	     graphicsContext.fillText("GAME OVER", 125, 150);       
	 }
	 
	private void drawShortCutInformation(GraphicsContext graphicsContext) {
		graphicsContext.setFont(new Font("Courier New", 20));
	    graphicsContext.setFill(Color.RED);
	    graphicsContext.fillText("Hit 'SPACEBAR' to play again!", 100, 250);
	        
	    graphicsContext.setFont(new Font("Courier New", 20));
	    graphicsContext.setFill(Color.RED);
	    graphicsContext.fillText("Hit 'M' to go back to the menu", 100, 270);
	        
	    graphicsContext.setFont(new Font("Courier New", 20));
	    graphicsContext.setFill(Color.RED);	
	    graphicsContext.fillText("Hit 'ESC' or 'Q' to quit Snake", 100, 290);
	 }
	 
	public void viewScore(GraphicsContext graphicsContext, BoardController boardController) {
		String scoreText = String.format("Score: %s", boardController.getBoard().getFruitScore());
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
	 
	 public void drawNewHighscoreText(GraphicsContext graphicsContext) {
		graphicsContext.setFont(new Font("Courier New", 40));
		graphicsContext.setFill(Color.WHITE);
		graphicsContext.fillText("NEW HIGHSCORE!!!", 75, 150);
	 }
	 
	 private void setGameOver() {
        this.game.setGameOver();
    }
    
	 public boolean getIsGameOver() {
        return this.game.getIsGameOver();
    }
	
}