package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import controller.BoardController;
import controller.FileHandler;
import model.BoardModel;

public class FileHandlerTest {
	
	private FileHandler fileHandler = new FileHandler();
	private BoardModel boardModel;
	private File file;

	
//	@BeforeEach
//	public void setup() {
//		//fileHandler = new FileHandler();
//	}
	
	@Test
	public void writeToFile() {
		BoardModel boardModel = new BoardModel(50, 50, 10);
		BoardController boardController = new BoardController(boardModel);
		
//		fileHandler.writeScoreToFile(boardController);
//		System.out.println(fileHandler.getScoresFromFile("testscorefile.txt"));
//		fileHandler.getAppStateFile("testscorefile.txt");
//		
		fileHandler.writeScoreToFile(boardController, "testescorefile.txt");
		System.out.println(fileHandler.getScoresFromFile("testscorefile.txt"));
//		try {
//			fileHandler.writeScoreToFile(boardController, "testescorefile.txt");
//		}
//		catch(FileNotFoundException e) {
//			fail("Could not write to the file");
//			return;
//		}

//			System.out.println(fileHandler.getScoresFromFile());
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		
		
		
		
//		FileHandler savedNewGame; // Required to ignore Eclipse warning
//		try {
//			savedNewGame = fileHandler.getScoresFromFile("test-save");
//		} catch (FileNotFoundException e) {
//			fail("Could not load saved file");
//			return;
//		}
//		assertEquals(fileHandler.toString(), savedNewGame.toString());
//		assertFalse(boardModel.getIsGameOver());
//	}
	}
}

