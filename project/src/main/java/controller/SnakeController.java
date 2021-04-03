package controller;

import java.util.List;
import java.util.ArrayList;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.BoardModel;
import model.Coordinate;
import model.SnakeModel;
import model.FruitModel;
import controller.BoardController;

public class SnakeController {
    
    public SnakeModel snakeModel;
    
    public SnakeController(SnakeModel model) {
        this.snakeModel = model;
    }
    
    public void move(){
        ArrayList<Coordinate> snake = this.snakeModel.getSnake();
     
        Coordinate head = snake.get(0);
        
        
        //System.out.println(snake);
        
        for (int i = snake.size() - 1; i > 0; i--) {
            snake.get(i).setX(snake.get(i-1).getX());
            snake.get(i).setY(snake.get(i-1).getY());
        }
        
        switch (snakeModel.getDirection()) {
            case up:
                head.setY(head.getY()-1);
                break;
            case down:
                head.setY(head.getY()+1);
                break;
            case left:
                head.setX(head.getX()-1);
                break;
            case right:
                head.setX(head.getX()+1);
                break;
            default:
                break;
        }
    }
    
    public SnakeModel getSnakeModel() {
    	return snakeModel;
    }
    
    public void fillSnake(GraphicsContext graphicsContext, BoardModel board) {
        ArrayList<Coordinate> snake = this.snakeModel.getSnake();
        int pxSize = board.getPixelSize();
        	
        graphicsContext.setFill(Color.WHITE); // fill the background white each time
        graphicsContext.fillRect(0, 0, board.getWidth()*pxSize, board.getHeight()*pxSize); // For å ikke dobbeltegne score

        
   
        graphicsContext.setFill(Color.BLACK); // fill the background white each time
        graphicsContext.fillRect(0, 0, board.getWidth()*pxSize, board.getHeight()*pxSize - SnakeApp.SCOREBOARD_BANNER_HEIGHT); // Trekker fra 30 for å gi plass til scoreboard.

        
        for (Coordinate s : snake) { // draw snake
            graphicsContext.setFill(this.snakeModel.getSnakeColor());
            graphicsContext.fillRect(s.getX()*pxSize, s.getY()*pxSize, pxSize - 1, pxSize - 1);
        }
    }
    
    public void eatFruit(BoardController boardController) {
    	Coordinate snakeHead = this.snakeModel.getSnake().get(0);
        FruitModel fruit = boardController.getBoard().getFruit();
    	//System.out.println(fruit);
      
    	
        if (snakeHead.getX() == fruit.getPositionX() && snakeHead.getY() == fruit.getPositionY()) {
            this.snakeModel.getSnake().add(new Coordinate(-1,-1));
            this.snakeModel.setIncreasedSpeed();
            boardController.getBoard().increaseFruitScore();
            boardController.playEatingSound("eatingSound.wav");
            boardController.getBoard().setFruit();
        }
    }
    
    
    public boolean snakeCrashed(BoardModel board) {
        ArrayList<Coordinate> snake = this.snakeModel.getSnake();
        boolean crashed = false;
        Coordinate head = snake.get(0);
        List<Coordinate> body = snake.subList(1, snake.size()); 
        
       // System.out.println(body.contains(fruit));
        
        for (int j = 0; j < body.size(); j++) {
	   		 if (head.getX() == body.get(j).getX() && head.getY() == body.get(j).getY()){
	   			 return true;
	   		 }
        }
        
        switch (snakeModel.getDirection()) {
        	
        
            case up:
            	crashed = (head.getY()  + 1) <= 0;
            	break;
            case down:
                crashed = (head.getY() + 1) >= (board.getHeight() - (SnakeApp.SCOREBOARD_BANNER_HEIGHT / SnakeApp.PIXEL_SIZE)); // TREKKER FRA 30 FOR Å GI PLASS TIL SCOREBOARD
                break;
            case left:
                crashed = (head.getX() + 1) <= 0;
                break;
            case right:
                crashed = (head.getX() + 1) >= board.getWidth();
                break;
            default:
                break;
        }
      
        return crashed;
    }
}
 