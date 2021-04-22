package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controller.SnakeController;
import model.BoardModel;
import model.SnakeModel;
import utils.Dir;


public class SnakeModelTest {
	
	private SnakeModel snakeModel;
	private SnakeModel snakeModel2;
	
	@BeforeEach
	public void setup() {
		snakeModel = new SnakeModel(5, 2);
		snakeModel2 = new SnakeModel(8, 2);
		
	}
	
	@Test
	public void testConstructor() {
		assertEquals(snakeModel.getInitSize(), 5);
		assertEquals(snakeModel.getSpeed(), 2);
		
		
	}
	
	@Test
	public void testSnakeCrashed() {
		BoardModel boardModel = new BoardModel(50, 50, 10);
		
		// Case 1: 
		SnakeController snakeController = new SnakeController(snakeModel);
		snakeController.move();
		boolean case1_notCrashed = snakeController.snakeCrashed(boardModel);
		assertEquals(case1_notCrashed, false);
		snakeModel.setDirection(Dir.up);
		snakeController.move();
		boolean case1_crashed = snakeController.snakeCrashed(boardModel);
		assertEquals(case1_crashed, true);
		
		// Case 2:
		SnakeController snakeController2 = new SnakeController(snakeModel2);
		for (int i = 0; i < (boardModel.getWidth() / 2); i++) {
			snakeController2.move();
		}
		boolean case2_notCrashed = snakeController2.snakeCrashed(boardModel);
		assertEquals(case2_notCrashed, false);
		
		snakeModel2.setDirection(Dir.down);
		for (int i = 0; i < (boardModel.getHeight() / 2); i++) {
			snakeController2.move();
		}
		boolean case2_notCrashed2 = snakeController2.snakeCrashed(boardModel);
		assertEquals(case2_notCrashed2, false);
		
		snakeModel2.setDirection(Dir.right);
		for (int i = 0; i < boardModel.getWidth(); i++) {
			snakeController2.move();
		}
		boolean case2_crashed = snakeController2.snakeCrashed(boardModel);
		assertEquals(case2_crashed, true);	
	}
}