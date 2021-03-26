package controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;


public class StartMenuController implements Initializable{
	
	public static String username; 
	
	public FileHandler fileHandler;
	
	@FXML
	private TextField usernameInput;
	
	@FXML
	private Text usernameException;
	
	@FXML
	private Button startGameButton;
	
	@FXML 
	public TextArea highscoreText;
	
	@FXML
	private void setUsername() {
		
		username = usernameInput.getText();
		if(invalidUsername(username)) {
			startGameButton.setDisable(false);
		}
	
		System.out.println(username);
		//String usernameString = username.getText();
	}
	
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		String scores = FileHandler.getScoresFromFile();
		highscoreText.appendText(scores);
		
	}
	
	private boolean invalidUsername(String username) {
		if(username.length() <= 3 || !username.matches("[a-zA-Z]*$")) {
			usernameException.setVisible(true);
			return false;
			//throw new IllegalArgumentException("Invalid username. Username must be 4 digits, and contain of letters or numbers.");
		}
		usernameException.setVisible(false);
		return true;
	}
	
	public static String getUsername() {
		return username;
	}
	
	
	public void startGameButton() throws Exception {
		SnakeApp.startSnake();
	}
	
	
}
