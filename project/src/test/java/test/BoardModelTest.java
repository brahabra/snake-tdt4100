package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.BoardModel;
import controller.StartMenuController;

public class BoardModelTest {
	
	private BoardModel boardModel;

	
	@BeforeEach
	public void setup() {
		boardModel = new BoardModel(12, 16, 10);
	}
	
	@Test
	public void testConstructor() {
		assertEquals(boardModel.getHeight(), 16);
		assertEquals(boardModel.getWidth(), 12);
		assertEquals(boardModel.getPixelSize(), 10);
	}
	
	@Test
	public void testFruitScore() {
		assertEquals(boardModel.getFruitScore(), 0);
		boardModel.increaseFruitScore();
		boardModel.increaseFruitScore();
		assertEquals(boardModel.getFruitScore(), 2);
		boardModel.resetFruitScore();
		assertEquals(boardModel.getFruitScore(), 0);
	}
	
	@Test
	public void testGameOver() {
		assertEquals(boardModel.getIsGameOver(), false);
		boardModel.setGameOver();
		assertEquals(boardModel.getIsGameOver(), true);
	}
	
	@Test
	public void testInvalidUsername() {
		StartMenuController startMenuController = new StartMenuController();
		boolean valid = startMenuController.invalidUsername("Username12");
		assertEquals(valid, true);
		boolean invalid = startMenuController.invalidUsername("");
		assertEquals(invalid, false);
		//TODO Legge til flere edge-caser
	}
	
}
