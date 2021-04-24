package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeAll;
import controller.BoardController;
import controller.FileHandler;
import model.BoardModel;

public class FileHandlerTest {
	
	private static FileHandler fileHandler = new FileHandler();
	private static String testFile = "TESTFILE.txt";
	
	BoardModel boardModel = new BoardModel(50, 50, 10);
	BoardController boardController = new BoardController(boardModel);
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	Date date = new Date();  

	@BeforeAll
	public static void setup() throws IOException {
		File file = fileHandler.getAppStateFile(testFile);
		file.createNewFile();
	}
	
	@Test
	@DisplayName("Check if information is written correctly to file")
	public void writeToFile() {
		
		boardModel.setUsername("Rudolf99");
		boardModel.setFruitScore(17);
		
		// Blir litt hjemmesnekret format på dette. Men mellomrommene er fordi det blir slik fra formateringen med padRight/padLeft i Highscore.java
		// Men testingen oppfører seg likevel riktig. 
		

		String correctText = " 1.     " + boardModel.getFruitScore() + "       " + boardModel.getUsername() + "        " +   formatter.format(date).substring(0, 16) + "\n";
		String wrongText = " 1.     " + boardModel.getUsername() + "       " + boardModel.getFruitScore() + "        " +   formatter.format(date).substring(0, 16) + "\n";
		
		try {
			fileHandler.writeScoreToFile(boardController, testFile);	
		
		} catch(Exception e) {
			fail("Could not write to file");
			return;
		}
		
		assertEquals(correctText, fileHandler.getScoresFromFile(testFile));
		assertNotEquals(wrongText, fileHandler.getScoresFromFile(testFile));
	}
	
	@Test
	@DisplayName("Check if reading from file is correct")
	public void readFromFile() {
	       
		boardModel.setUsername("Rudolf99");
		boardModel.setFruitScore(17);
		
		String correctText = "1.     " + boardModel.getFruitScore() + "       " + boardModel.getUsername() + "        " +   formatter.format(date).substring(0, 16) +"\n";
		String wrongText = "1.     " + boardModel.getUsername() + "       " + boardModel.getFruitScore() + "        " +   formatter.format(date).substring(0, 16) +"\n";
		String fileOutput;
		
		try {
		 fileOutput = fileHandler.getScoresFromFile(testFile);
		
		} catch(Exception e) {
			fail("Could not read from the file");
			return;
		}
		
		assertEquals(correctText, fileOutput.substring(1));
		assertNotEquals(wrongText, fileOutput.substring(1));	
	}
	
	@AfterAll
	public static void cleanUp() {
		fileHandler.getAppStateFile(testFile).delete();
	}
}