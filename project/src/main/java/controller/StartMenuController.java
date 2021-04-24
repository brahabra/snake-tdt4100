package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class StartMenuController implements Initializable{
	
	public static String username; 
	public static int totalScore;	
	public static int totalGames;
	private FileHandler fh = new FileHandler();
	
	@FXML
	private TextField usernameInput;
	
	@FXML
	private Text usernameException;
	
	@FXML
	private Button startGameButton;
	
	@FXML 
	private  TextArea highscoreText;
	
	@FXML 
	private  TextField averageScore;

	@FXML 
	private  TextField totalGamesField;
	
	@FXML
	private ImageView snakePicture;
	
	@FXML
	private Button refreshButton;
	
	@FXML
	private void setUsername() {
		username = usernameInput.getText();
		if(invalidUsername(username)) {
			startGameButton.setDisable(false);
			usernameException.setVisible(false);
		}
		else {
			usernameException.setVisible(true);
		}
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		usernameException.setVisible(false);
		loadHighscores();
		
		try {
			loadPicture();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
		
	public boolean invalidUsername(String username) {
		
		return (username.matches("^(?=.{4,10}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$"));	
	}

	
	public static String getUsername() {
		return username;
	}
		
	public void loadHighscores() {
		String scores = fh.getScoresFromFile("scorefile.txt");
		
		if(totalGames != 0 && totalScore != 0) {
			highscoreText.appendText(scores);
			totalGamesField.setText(Integer.toString(totalGames));
			averageScore.setText(Integer.toString(totalScore / totalGames));
		}
		
		else {
			totalGamesField.setText(Integer.toString(totalGames));
			averageScore.setText(Integer.toString(0));
			highscoreText.appendText(scores);
		}
	}
	
	public void loadPicture() throws Exception {
		File file = new File("../project/src/main/resources/snakePictureAnimated.png");
		Image image = new Image(file.toURI().toString());
	    snakePicture.setImage(image);
	    
	    if(!file.exists()) {
			throw new FileNotFoundException("Could not find the requested picture file");
		}
	}
		
	public void refreshHighscores() {
		FileHandler fh = new FileHandler();
		String scores = fh.getScoresFromFile("scorefile.txt");
		
		if(totalGames != 0 && totalScore != 0) {
			highscoreText.setText(scores);
			totalGamesField.setText(Integer.toString(totalGames));
			averageScore.setText(Integer.toString(totalScore / totalGames));
		}
		
		else {
			totalGamesField.setText(Integer.toString(totalGames));
			averageScore.setText(Integer.toString(0));
			highscoreText.setText(scores);
		}
	}
	
	public int getAverageScore() {
		return totalScore / totalGames;
	}
	
	public void startGameButton() throws Exception {
		SnakeApp snakeApp = new SnakeApp();
		
		if(invalidUsername(usernameInput.getText())) {
			snakeApp.startSnake();
	
		}
		else {
			usernameException.setVisible(true);
			startGameButton.setDisable(true);
		
		}	
	}
}
