package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FileHandler implements FileHandlerInterface{
	
	public static int topTenScore;
	public static int topFiveScore;
	public static int highscoreScore;
	
	public File getAppStateFile(String fileName) {
		return new File(System.getProperty("user.home") + "/Applications/tdt4100-app/" + fileName);
	}

	public String getScoresFromFile(String filename){
		BufferedReader reader;
		File file = getAppStateFile(filename);
		String output = "";
		List<Highscore> allScores = new ArrayList<Highscore>();
		
		StartMenuController.totalGames = 0;
		StartMenuController.totalScore = 0;
		
		try {
				if (!file.exists()) {
					file.getParentFile().mkdirs();
					file.createNewFile();
				}
			
				reader = new BufferedReader(new FileReader(file));
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
			// En tilbakemelding til brukeren dersom filen man leser fra ikke inneholder noen scores. Da må man bare spille et game så er det i gang
			if (output == "") {
				output = "ERROR:\nThe list could not be loaded from the file '" + file + "' because it is empty.\nPlease play a game to append a score to the list!";
				return output;
			}
		
			else {
				return output;
			}
		} 
		catch(Exception e) {
			System.out.println("Could not read from requested file '" + getAppStateFile("scorefile.txt") + "'.");
			e.printStackTrace();
		}
		return output;
		
	}	
		
	// Hentet fra https://beginnersbook.com/2014/01/how-to-write-to-a-file-in-java-using-fileoutputstream/
	public void writeScoreToFile(BoardController boardController, String filename) {
		
		FileOutputStream fos = null;
		File file;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  
		String outputToFile =  boardController.getBoard().getFruitScore() +  ";" + formatter.format(date) + ";" + StartMenuController.getUsername() + "\n";
	    
	    try {
	    	file = getAppStateFile(filename);
	    	fos = new FileOutputStream(file, true);
	    	
	    	if (!file.exists()) {
	    		file.createNewFile();
	    	}
	    	
	    	byte[] bytesArray = outputToFile.getBytes();
	    	fos.write(bytesArray);
	    	fos.flush();
	    		    	
	    }
	    catch (IOException ioe) {
	    	System.out.println("Could not write to the requested file '" + getAppStateFile("scorefile.txt") + "'.");
	    	ioe.printStackTrace();
	    }
	    
	    finally {
	    	try {
	    		if (fos != null);
	    		{
	    			fos.close();
	    		}
	    	}
	    	catch(IOException ioe) {
	    	System.out.println("Error in closing the stream");
	    	}
	    }
	}
}
