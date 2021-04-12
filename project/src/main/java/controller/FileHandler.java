package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.BoardModel;

public class FileHandler implements FileHandlerInterface{
	

	public StartMenuController startMenuController;
	public static BoardController game;
	public static int topTenScore;
	public static int topFiveScore;
	public static int highscoreScore;
	
	public String getScoresFromFile(String filename){
		BufferedReader reader;
		String output = "";
		List<Highscore> allScores = new ArrayList<Highscore>();
		
		try {
				
				reader = new BufferedReader(new FileReader(filename));
				String line = reader.readLine();
				
				while(line != null) {
					String[] parts = line.split(";");
					Integer points = Integer.valueOf(parts[0]);
					StartMenuController.totalScore += points;
					StartMenuController.totalGames += 1;
					String time = parts[1].substring(0, 16);
					String name = parts[2];
					Highscore newHighscore = new Highscore(name, points, time);
					
					allScores.add(newHighscore);
					
					//StartMenuController.highscoreText.appendText("POINTS: " + points + ". USERNAME: " + name + ". TIME: " + time + "\n");
		
					//System.out.println(points + " " +  time + " " +  name);
					
					//System.out.println(parts);
					//output += "POINTS: " + points + ". USERNAME: " + name + ". TIME: " + time + "\n";
					line = reader.readLine();
				
			}
			reader.close();
			
			Collections.sort(allScores);
			Collections.reverse(allScores);
			
			int placement = 0;
			
			for (Highscore highscore : allScores) {
					placement ++;
					if(placement <= 10) {
						if(placement == 10) {
							topTenScore = highscore.getPoints();
						}
						
						if(placement == 5) {
							topFiveScore = highscore.getPoints();
						}
						
						if(placement == 1) {
							highscoreScore = highscore.getPoints();
						}
						
						// Litt "stygg" koding bak formateringen på highscores, men :
						
						// Case som oppstår hvis man er på topplista (1-9.plass), men har mindre enn 10 poeng.(Veldig sjeldent
						// dette vil skje). Da legges det til litt space her og der. Samt fjerne punktumen
						// i toString-en og heller lage et selv her. 
						if(highscore.getPoints() < 10 && placement != 10) {
							output += " "  + placement + ". " + highscore.toString().substring(1) + "\n";
						}
						
						// Samme case som ovenfor, men utligne nå lengden på tallet ti samt mindre enn ti poeng.
						else if(highscore.getPoints() < 10 && placement == 10) {
							output += placement + ". " + highscore.toString().substring(1) + "\n";
						}
						
						// Hvis man på 1-9.plass og ikke har mindre enn 10 poeng, så formateres det slik. Bare
						// legger på en space på plasseringen
						else if(placement != 10) {
							output += " " + placement + highscore.toString() + "\n";
						}
						// Unngår å legge til et space dersom man er på 10.plass.
						else {
							output += placement + highscore.toString() + "\n";
							
						}
					}
					
				}

			return output;
			
		} catch(Exception e) {
			System.out.println("Could not find the requested file");
			e.printStackTrace();
		}
		return output;
		
	}	
	
	public void writeScoreToFile(String filename) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  
	    
		try {
			FileWriter fw = new FileWriter(filename, true);
			fw.write(BoardController.game.getFruitScore() + ";" + formatter.format(date) + ";" + StartMenuController.getUsername() + "\n");
			fw.close();
		}
		catch(IOException ioe) 
		{
			System.err.println("IOException " + ioe.getMessage());
		}
	}
	
	
}
