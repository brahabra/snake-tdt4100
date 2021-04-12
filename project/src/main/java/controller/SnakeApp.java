package controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
//import jdk.internal.org.jline.terminal.Terminal;
import model.BoardModel;
import model.SnakeModel;
import utils.Dir;

public class SnakeApp extends Application {

	public static final int BOARD_WIDTH = 50;
	public static final int PIXEL_SIZE = 10;
	public static final int BOARD_HEIGHT = 50;
	public static final int SCOREBOARD_BANNER_HEIGHT = 30;   // Gitt at denne verdien går opp i pixel_size, hvis ikke så havner hodet litt utenfor. 
															 // BannerHeight må også være større enn pixelSize 
	
	public static Stage startStage;
	
	public void start(Stage primaryStage) {
		//StartMenuController a = new StartMenuController();
		
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("StartMenu.fxml"));
			primaryStage.setScene(new Scene(parent, BOARD_WIDTH * PIXEL_SIZE, BOARD_HEIGHT*PIXEL_SIZE));
		
			primaryStage.setTitle("Start Menu");
			primaryStage.show();
			SnakeApp.startStage = primaryStage;
		
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
		
	//@Override
	public static void startSnake() throws Exception {
		

		Stage primaryStage = new Stage();
		
		BorderPane root = new BorderPane();
		Canvas canvas = new Canvas(BOARD_WIDTH*PIXEL_SIZE, BOARD_HEIGHT*PIXEL_SIZE);
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		Scene scene = new Scene(root, BOARD_WIDTH*PIXEL_SIZE, BOARD_HEIGHT*PIXEL_SIZE);
		BoardModel board = new BoardModel(BOARD_WIDTH, BOARD_HEIGHT,PIXEL_SIZE);
		BoardController boardController = new BoardController(board);
		SnakeModel snake = new SnakeModel(5,5); 
		boardController.startSnake(scene, graphicsContext, snake);
		
		scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
			
			switch (key.getCode()) { 
				case W: 
				case UP:
					if (snake.getDirection() != Dir.down) {
						snake.setDirection(Dir.up);
					}
					
					System.out.println("OPP");
					break;
				case S:
				case DOWN:
					if (snake.getDirection() != Dir.up) {
						snake.setDirection(Dir.down);
					}
					System.out.println("NED");
					break;
				case A:
				case LEFT:
					if (snake.getDirection() != Dir.right) {
						snake.setDirection(Dir.left);
					}
					System.out.println("VENSTRE");
			
					break;
				case D:
				case RIGHT:
					System.out.println("HØYRE");
					if (snake.getDirection() != Dir.left) {
						snake.setDirection(Dir.right);
					}
					break;
				
				case M:
					//SnakeApp.main(null);
					if(BoardController.getIsGameOver()) {
						
						//StartMenuController.
						System.out.println("Startmenyen vises");
						startStage.close();
						startStage.show();
						primaryStage.close();
					}
					break;
				case Q:
					if(BoardController.getIsGameOver()) {
					System.out.println("Avslutter spillet.");
					System.exit(0);
					}
					break;
				case SPACE:
					if(BoardController.getIsGameOver()) {
						System.out.println("Spillet startet på nytt!");
						try {
							primaryStage.close(); // Lukker spillet
							SnakeApp.startSnake(); // Starter spillet på nytt
						} catch (Exception e) {
							e.printStackTrace();
						}	
						
					}
					break;
				default:
				break;
			}
		});
		
		primaryStage.setTitle("Play Snake");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}

	public static void main(String[] args) {
		launch(SnakeApp.class, args);
		
		
		
	}
}
