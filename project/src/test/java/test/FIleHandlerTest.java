package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.FileHandler;
import model.BoardModel;

public class FIleHandlerTest {
	
	private FileHandler filehandler;
	private BoardModel boardmodel;
	
	@BeforeEach
	public void setup() {
		filehandler = new FileHandler();
	}
	
	@Test
	public void test() {
		FileHandler savedNewGame; // Required to ignore Eclipse warning
//		try {
//			savedNewGame = filehandler.getScoresFromFile("test-save");
//		} catch (FileNotFoundException e) {
//			fail("Could not load saved file");
//			return;
//		}
//		assertEquals(filehandler.toString(), savedNewGame.toString());
//		assertFalse(boardmodel.getIsGameOver());
	}
}

