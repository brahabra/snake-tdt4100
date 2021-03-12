package controller;

import javafx.stage.Stage;

public class StartMenuController {
	
	
	
	public void startGameButton() throws Exception {
		System.out.println("Du trykket på start game-knappen :D");
		
		SnakeApp.startSnake();
	
	}
	
	public void viewHighscoresButton() throws Exception{
		System.out.println("Du trykket på highscore :D");
		
		SnakeApp.viewHighscores();
		
	}
	
//	public static void main(String[] args) {
//		
//	}
}
