package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.BoardModel;

public class BoardModelTest {
	
	private BoardModel boardmodel;
	
	@BeforeEach
	public void setup() {
		boardmodel = new BoardModel(12, 16, 10);
	}
	
	@Test
	public void testConstructor() {
		assertEquals(boardmodel.getHeight(), 16);
		assertEquals(boardmodel.getWidth(), 12);
		assertEquals(boardmodel.getPixelSize(), 10);
	}
	
	@Test
	public void testFruitScore() {
		assertEquals(boardmodel.getFruitScore(), 0);
		boardmodel.increaseFruitScore();
		boardmodel.increaseFruitScore();
		assertEquals(boardmodel.getFruitScore(), 2);
		boardmodel.resetFruitScore();
		assertEquals(boardmodel.getFruitScore(), 0);
	}
}
