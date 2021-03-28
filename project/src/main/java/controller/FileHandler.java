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
						if(placement != 10) {
							output += " " + placement + highscore.toString() + "\n";
						}
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
