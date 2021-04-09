package controller;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.imageio.ImageIO;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.BoardModel;

public class StartMenuController implements Initializable{
	
	public static BoardModel game; 
	public static String username; 
	public static int totalScore = 0;	
	public static int totalGames = 0;
	public FileHandler fileHandler;
	
	@FXML
	private TextField usernameInput;
	
	@FXML
	private Text usernameException;
	
	@FXML
	private Button startGameButton;
	
	@FXML 
	public  TextArea highscoreText;
	
	@FXML 
	public  TextField averageScore;
	
	@FXML 
	public TextField totalGamesField;
	
	@FXML
	public ImageView snakePicture;
	
	@FXML
	public Button refreshButton;
	
	@FXML
	private void setUsername() {
		
		username = usernameInput.getText();
		if(invalidUsername(username)) {
			startGameButton.setDisable(false);
		}

	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		usernameException.setVisible(false);
		loadHighscores();
		loadPicture();
	}
	
	private boolean invalidUsername(String username) {
		
		//if(username.length() <= 3 || username.length() >= 10 || !username.matches("^.*[a-zA-Z0-9]+.*$")) {
		if(!username.matches("^(?=.{4,10}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$"))	{
			usernameException.setVisible(true);
			return false;
			//throw new IllegalArgumentException("Invalid username. Username must be between 4 and 10 digits, and contain of letters or numbers.");
		}
			usernameException.setVisible(false);
			return true;
	}
	
	public static String getUsername() {
		return username;
	}
		
	public void loadHighscores() {
		FileHandler fh = new FileHandler();
		String scores = fh.getScoresFromFile("scorefile.txt");
		
		highscoreText.appendText(scores);
		averageScore.setText(Integer.toString(totalScore / totalGames));
		totalGamesField.setText(Integer.toString(totalGames));

	}
	
	public void loadPicture() {
		File file = new File("snakePictureAnimated.png");
        Image image = new Image(file.toURI().toString());
        snakePicture.setImage(image);
	}
	
	
	public void refreshHighscores() {
		FileHandler fh = new FileHandler();
		String scores = fh.getScoresFromFile("scorefile.txt");
		highscoreText.setText(scores);
		
	}
	
	public void startGameButton() throws Exception {
		if(invalidUsername(usernameInput.getText())) {
			SnakeApp.startSnake();
			SnakeApp.startStage.close();
		}
		else {
			startGameButton.setDisable(true);
		
		}	
	
	}
}
