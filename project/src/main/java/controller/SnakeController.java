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
    
    private SnakeModel snakeModel;
    
    public SnakeController(SnakeModel model) {
        this.snakeModel = model;
    }
    
    public void move(){//BoardController boardController) {
        ArrayList<Coordinate> snake = this.snakeModel.getSnake();
        Coordinate head = snake.get(0);
        
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
                 
      //  eatFruit();
        
       
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
            //graphicsContext.setFill(this.snakeModel.getSnakeColor());
            //graphicsContext.fillRect(s.getX()*pxSize, s.getY()*pxSize, pxSize - 2, pxSize - 2);
        }
    }
    
    public void eatFruit(BoardController boardController) {
    	Coordinate snakeHead = this.snakeModel.getSnake().get(0);
        FruitModel fruit = boardController.getBoard().getFruit();
    	//FruitModel fruit = boardController.getFruit(1, 2);
    	
        if (snakeHead.getX() == fruit.getPositionX() && snakeHead.getY() == fruit.getPositionY()) {
            this.snakeModel.getSnake().add(new Coordinate(-1,-1));
            this.snakeModel.setIncreasedSpeed();
            boardController.getBoard().increaseFruitScore();
            boardController.getBoard().setFruit();
        }
    }
    
    
    public boolean snakeCrashed(BoardModel board) {
        ArrayList<Coordinate> snake = this.snakeModel.getSnake();
        boolean crashed = false;
        Coordinate head = snake.get(0);
        List<Coordinate> body = snake.subList(1, snake.size()); 
        //Coordinate head1 = snake.get(1);
        //Coordinate head2 = snake.get(2);
        //Coordinate head3 = snake.get(3);
        
        //Coordinate head1 = snake.get(1);
       //System.out.println(head.getX());
       //System.out.println(head.getY());
       //System.out.println("");
    
        for (int j = 0; j < body.size(); j++) {
        	//System.out.println(body.get(j).getX());
        	//System.out.println(body.get(j).getY());
	   		 if (head.getX() == body.get(j).getX() && head.getY() == body.get(j).getY()){
	   			 return true;
	   		 }
        }
        
        switch (snakeModel.getDirection()) {
        	
        
            case up:
            	//crashed = head.getY() < 0 || (head.getX() == head1.getX() && head.getY() == head1.getY()) || (head.getX() == head2.getX() && head.getY() == head2.getY()) || (head.getX() == head3.getX() && head.getY() == head3.getY());
            	crashed = (head.getY()  + 1) <= 0;
            	
            	/*
            	for (int j = 1; j < snake.size(); j++ ){
            		Coordinate head1 = snake.get(j);
            		crashed = head.getY() < 0 || (head.getY() == head1.getY() && head.getX() == head1.getX());
            	}
            	*/
            	break;
      
               // break;
            case down:
                crashed = (head.getY() + 1) >= (board.getHeight() - (SnakeApp.SCOREBOARD_BANNER_HEIGHT / SnakeApp.PIXEL_SIZE)); // TREKKER FRA 30 FOR Å GI PLASS TIL SCOREBOARD
            	//crashed = head.getY() > board.getHeight()|| (head.getX() == head1.getX() && head.getY() == head1.getY()) || (head.getX() == head2.getX() && head.getY() == head2.getY()) || (head.getX() == head3.getX() && head.getY() == head3.getY());
               
                break;
            case left:
                crashed = (head.getX() + 1) <= 0;
            	//crashed = head.getY() < 0 || (head.getX() == head1.getX() && head.getY() == head1.getY()) || (head.getX() == head2.getX() && head.getY() == head2.getY()) || (head.getX() == head3.getX() && head.getY() == head3.getY());
             
                break;
            case right:
                crashed = (head.getX() + 1) >= board.getWidth();
            	//crashed = head.getX() > board.getWidth() || (head.getX() == head1.getX() && head.getY() == head1.getY()) || (head.getX() == head2.getX() && head.getY() == head2.getY()) || (head.getX() == head3.getX() && head.getY() == head3.getY());
              
                break;
            default:
                break;
        }
        
        return crashed;
    }
}
 