package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.BoardModel;
import model.FruitModel;
import controller.BoardController;
import controller.StartMenuController;

public class BoardModelTest {
	
	private BoardModel boardModel;

	
	@BeforeEach
	public void setup() {
		boardModel = new BoardModel(50, 50, 10);
	}
	
	@Test
	@DisplayName("Check if construtor is correctly")
	public void testConstructor() {
		assertEquals(boardModel.getHeight(), 50);
		assertEquals(boardModel.getWidth(), 50);
		assertEquals(boardModel.getPixelSize(), 10);
	}
	
	@Test
	@DisplayName("Check if fruitscore is increasing and reseting correctly")
	public void testFruitScore() {
		assertEquals(boardModel.getFruitScore(), 0);
		boardModel.increaseFruitScore();
		boardModel.increaseFruitScore();
		assertEquals(boardModel.getFruitScore(), 2);
		boardModel.resetFruitScore();
		assertEquals(boardModel.getFruitScore(), 0);
	}
	
	@Test
	@DisplayName("Check if game over is registered")
	public void testGameOver() {
		assertEquals(boardModel.getIsGameOver(), false);
		boardModel.setGameOver();
		assertEquals(boardModel.getIsGameOver(), true);
	}
	
	@Test
	@DisplayName("Checking if illegal username cannot be used")
	public void testInvalidUsername() {
		StartMenuController startMenuController = new StartMenuController();
		
		boolean valid = startMenuController.invalidUsername("Username17");
		assertEquals(valid, true);
		boolean valid2 = startMenuController.invalidUsername("12_34");
		assertEquals(valid2, true);
		boolean valid3 = startMenuController.invalidUsername("a.Z.l.W9_q");
		assertEquals(valid3, true);
		boolean valid4 = startMenuController.invalidUsername("ABCD");
		assertEquals(valid4, true);
		
		boolean invalid = startMenuController.invalidUsername("");
		assertEquals(invalid, false);
		boolean invalid2 = startMenuController.invalidUsername("Brage-2001");
		assertEquals(invalid2, false);
		boolean invalid3 = startMenuController.invalidUsername("Brage 123");
		assertEquals(invalid3, false);
		boolean invalid4 = startMenuController.invalidUsername("Åshild78");
		assertEquals(invalid4, false);
	}
}