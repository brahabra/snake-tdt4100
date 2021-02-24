package controller;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.BoardModel;
import model.Coordinate;
import model.SnakeModel;

public class SnakeController {
    
    private SnakeModel snakeModel;
    
    public SnakeController(SnakeModel model) {
        this.snakeModel = model;
    }
    
    public void move(){//BoardController boardController) {
        ArrayList<Coordinate> snake = this.snakeModel.getSnake();
        Coordinate head = snake.get(0);
        
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
                 
       // eatFood(boardController);
        
        for (int i = snake.size() - 1; i > 0; i--) {
            snake.get(i).setX(snake.get(i-1).getX());
            snake.get(i).setY(snake.get(i-1).getY());
        }
    }
    
    public void fillSnake(GraphicsContext graphicsContext, BoardModel board) {
        ArrayList<Coordinate> snake = this.snakeModel.getSnake();
        int pxSize = board.getPixelSize();
        
        graphicsContext.setFill(Color.BLACK); // fill the background white each time
        graphicsContext.fillRect(0, 0, board.getWidth()*pxSize, board.getHeight()*pxSize);
       
        
        for (Coordinate s : snake) { // draw snake
            graphicsContext.setFill(this.snakeModel.getSnakeColor());
            graphicsContext.fillRect(s.getX()*pxSize, s.getY()*pxSize, pxSize - 1, pxSize - 1);
            //graphicsContext.setFill(this.snakeModel.getSnakeColor());
            //graphicsContext.fillRect(s.getX()*pxSize, s.getY()*pxSize, pxSize - 2, pxSize - 2);
        }
    }
    /*
    private void eatFood(BoardController boardController) {
    	Coordinate snakeHead = this.snakeModel.getSnake().get(0);
        FoodModel food = boardController.getBoard().getFood();
        if (snakeHead.getX() == food.getX() && snakeHead.getY() == food.getY()) {
            this.snakeModel.getSnake().add(new Coordinate(-1,-1));
            boardController.setNewFood();
        }
    }
    */
    
    public boolean snakeCrashed(BoardModel board) {
        ArrayList<Coordinate> snake = this.snakeModel.getSnake();
        boolean crashed = false;
        Coordinate head = snake.get(0);
        
        switch (snakeModel.getDirection()) {
            case up:
                crashed = head.getY() < 0;  
                break;
            case down:
                crashed = head.getY() > board.getHeight();
                break;
            case left:
                crashed = head.getX() < 0;
                break;
            case right:
                crashed = head.getX() > board.getWidth();
                break;
            default:
                break;
        }
        
        return crashed;
    }
}
 