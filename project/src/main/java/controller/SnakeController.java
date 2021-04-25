package controller;

import java.util.List;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.BoardModel;
import model.Coordinate;
import model.SnakeModel;
import model.FruitModel;


public class SnakeController {
    
    private SnakeModel snakeModel;
    
    public SnakeController(SnakeModel model) {
        this.snakeModel = model;
    }
    
    public void move(){
        ArrayList<Coordinate> snake = this.snakeModel.getSnake();
        Coordinate head = snake.get(0);
        
        // Looper gjennom kroppen utenom hodet til slangen. Oppdaterer slangeXY-verdiene kontinuerlig. 
        // "Forrige" gjøres til "ny". Looper ikke med hodet, stopper på i > 0 => i stopper på 1.
        for (int i = snake.size() - 1; i > 0; i--) {
    
            snake.get(i).setX(snake.get(i-1).getX());
            snake.get(i).setY(snake.get(i-1).getY());
        }
      
        // Switch-caser på hodet. Hva som skjer ved opp, venstre, høyre og ned. 
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
    
    // Fyller slange-lista med farge 
    public void fillSnake(GraphicsContext graphicsContext, BoardModel board) {
        ArrayList<Coordinate> snake = this.snakeModel.getSnake();
        int pxSize = board.getPixelSize();
        	
        graphicsContext.setFill(Color.WHITE); // fyller med hvit for å få infoen nederst
        graphicsContext.fillRect(0, 0, board.getWidth()*pxSize, board.getHeight()*pxSize); // For å ikke dobbeltegne score

        graphicsContext.setFill(Color.BLACK); //  fyller så med litt mindre svart på selve spillbrettet
        graphicsContext.fillRect(0, 0, board.getWidth()*pxSize, board.getHeight()*pxSize - SnakeApp.SCOREBOARD_BANNER_HEIGHT); // Trekker fra 30 for å gi plass til scoreboard.

        for (Coordinate s : snake) { // looper gjennom slangen
            graphicsContext.setFill(this.snakeModel.getSnakeColor());
            graphicsContext.fillRect(s.getX()*pxSize, s.getY()*pxSize, pxSize - 1, pxSize - 1); // trekke fra 1 for å gi en "ramme" rundt ruta
        }
    }
    
    public void eatFruit(BoardController boardController) {
    	Coordinate snakeHead = this.snakeModel.getSnake().get(0);
        FruitModel fruit = boardController.getBoard().getFruit();
 
        if (snakeHead.getX() == fruit.getPositionX() && snakeHead.getY() == fruit.getPositionY()) {
            this.snakeModel.getSnake().add(new Coordinate(-1,-1)); // Når slangen spiser frukt legges det til en ny Coordinate som er utenfor spillbrettet. Denne Coordinaten oppretters idet slangen spiser en frukt.
            this.snakeModel.setIncreasedSpeed();						 
            boardController.getBoard().increaseFruitScore();
            boardController.playEatingSound("../project/src/main/resources/eatingSound.wav");
            boardController.getBoard().setFruit();
       
            // Det gir ikke så altfor mening at man kan lagre midt i og så hente opp spillet senere,
            // fordi alt skjer så fort i Snake. så derfor har vi løst det ved at avhengig 
            // av highscorene man har i highscore-filen, så endrer slangen sin farge jo lengre 
            // man kommer i spillet.Har med større eller lik fordi vi har implementert slik at 
            // hvis tidspunktet er nyere enn en med samme score, så er det nyeste score som trumfer. 
            
            // Over top 1:
            if(boardController.getBoard().getFruitScore() >= FileHandler.highscoreScore) {
            	this.snakeModel.setSnakeColor(Color.WHITE);
            }
            
            //Innenfor top 5: 
            else if(boardController.getBoard().getFruitScore() >= FileHandler.topFiveScore) {
            	this.snakeModel.setSnakeColor(Color.YELLOW);
            }
            
            // Innenfor top 10: 
            else if(boardController.getBoard().getFruitScore() >= FileHandler.topTenScore) {
            	this.snakeModel.setSnakeColor(Color.SPRINGGREEN);
            }
        }
    }

    public boolean snakeCrashed(BoardModel board) {
        ArrayList<Coordinate> snake = this.snakeModel.getSnake();
        boolean crashed = false;
        Coordinate head = snake.get(0);
        List<Coordinate> body = snake.subList(1, snake.size()); 
        
        // Looper gjennom om hodet har kræsjet i alle kroppsdelene sine x og y-koordinater. 
        for (int j = 0; j < body.size(); j++) {
	   		 if (head.getX() == body.get(j).getX() && head.getY() == body.get(j).getY()){
	   			 return true;
	   		 }
        }
        // Sjekker om hodet har kræsjet i spillrammen. 
        // Plusser på 1 slik at man dør når man faktisk treffer rammen, hvis ikke så går man en blokk ut av brettet. 
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