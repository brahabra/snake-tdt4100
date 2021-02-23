package controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SnakeApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("Board.fxml"));
		primaryStage.setTitle("Snake");
		primaryStage.setScene(new Scene(parent));
		primaryStage.show();
		//parent.setOnKeyPressed(event -> { if (parent.getOnKeyPressed() != null) parent.getOnKeyPressed().keyPressed(event); });

	}

	public static void main(String[] args) {
		launch(SnakeApp.class, args);
	}
}
