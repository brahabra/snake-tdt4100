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
	
	@FXML
	private TextField usernameInput;
	
	@FXML
	private Text usernameException;
	
	@FXML
	private Button startGameButton;
	
	@FXML
	private TextArea highscoreText;

	
	@FXML
	private void setUsername() {
		
		username = usernameInput.getText();
		if(validateUsername(username)) {
			startGameButton.setDisable(false);
		}
	
		System.out.println(username);
		//String usernameString = username.getText();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		getScoresFromFile();
		
	}
	
	private boolean validateUsername(String username) {
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
		
		System.out.println("Du trykket på start game-knappen :D");
		SnakeApp.startSnake();
	}
	
	public void getScoresFromFile(){
		//try {
			
			//File myObj = new File("scorefile.txt");
//			
//			Scanner myReader = new Scanner(myObj);
//			while (myReader.hasNextLine()) {
//				
//				String data = myReader.readLine();
//				//System.out.println(data);
//				highscoreText.setText(data);
//				
//			}
//			myReader.close();
//		
//		
//		}catch (FileNotFoundException e){
//			System.out.println("Could not find the requested file " + filename + ".");
//			e.printStackTrace();
//		}
			BufferedReader reader;
			
			try {
				reader = new BufferedReader(new FileReader("scorefile.txt"));
				String line = reader.readLine();
				
				while(line != null) {
					String[] parts = line.split(";");
					String points = parts[0];
					String time = parts[1];
					String name = parts[2];
					System.out.println(points + " " +  time + " " +  name);
					
					//System.out.println(parts);
					highscoreText.appendText("POINTS: " + points + ". USERNAME: " + name + ". TIME: " + time + "\n");
					line = reader.readLine();
				}
				reader.close();
			} catch(Exception e) {
				System.out.println("Could not find the requested file");
				e.printStackTrace();
			}
			
	   }	
}
