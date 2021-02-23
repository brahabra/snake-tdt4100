package controller;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.BoardModel;
import model.SnakeModel;
import utils.Dir;


public class SnakeApp extends Application {

	public static final int BOARD_WIDTH = 50;
	public static final int PIXEL_SIZE = 10;
	public static final int BOARD_HEIGHT = 50;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//Parent parent = FXMLLoader.load(getClass().getResource("Board.fxml"));
		//primaryStage.setTitle("Snake");
		//Scene scene = new Scene(parent);
		//primaryStage.setScene(new Scene(parent));
		//primaryStage.show();
		//parent.setOnKeyPressed(event -> { if (parent.getOnKeyPressed() != null) parent.getOnKeyPressed().keyPressed(event); });
		
		BorderPane root = new BorderPane();
		Canvas canvas = new Canvas(BOARD_WIDTH*PIXEL_SIZE, BOARD_HEIGHT*PIXEL_SIZE);
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		Scene scene = new Scene(root, BOARD_WIDTH*PIXEL_SIZE, BOARD_HEIGHT*PIXEL_SIZE);
		BoardModel board = new BoardModel(BOARD_WIDTH, BOARD_HEIGHT,PIXEL_SIZE);
		BoardController boardController = new BoardController(board);
		SnakeModel snake = new SnakeModel(4,10); 
		boardController.start(scene, graphicsContext, snake);
		
		scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
			
			switch (key.getCode()) {
				case W:
				case UP:
					snake.setDirection(Dir.up);
					System.out.println("OPP");
					break;
				case S:
				case DOWN:
					snake.setDirection(Dir.down);
					System.out.println("NED");
					break;
				case A:
				case LEFT:
					System.out.println("VENSTRE");
					snake.setDirection(Dir.left);
					break;
				case D:
				case RIGHT:
					System.out.println("HØYRE");
					snake.setDirection(Dir.right);
					break;
				default:
					break;
			}
		});
		
		primaryStage.setTitle("Snake");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(SnakeApp.class, args);
	}
}
