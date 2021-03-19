package controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class StartMenuController {
	
	public static String username; 
	
	@FXML
	private TextField usernameInput;
	
	@FXML
	private Button startGameButton;
	
	@FXML
	private TextArea highscoreArea;

	
	@FXML
	private void setUsername() {
		getScoresFromFile("scorefile.txt");
		System.out.println("knappen ble trykket på");
		
		username = usernameInput.getText();
		
		startGameButton.setDisable(false);
		
		System.out.println(username);
		//String usernameString = username.getText();
		
	}
	
	public static String getUsername() {
		return username;
	}
	
	public void startGameButton() throws Exception {
		
		System.out.println("Du trykket på start game-knappen :D");
		
		SnakeApp.startSnake();
	}
	
	
	
//	// TODO LESE FRA FIL
	private void getScoresFromFile(String filename){
		try {
			File myObj = new File("scorefile.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				
				String data = myReader.nextLine();
				//System.out.println(data);
				highscoreArea.setText(data);
				
			}
			myReader.close();
		
		
		}catch (FileNotFoundException e){
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
			
	   }
	

}
