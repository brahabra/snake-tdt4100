package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.BoardController;
import controller.SnakeController;
import model.BoardModel;
import model.FruitModel;
import model.SnakeModel;
import utils.Dir;

public class FruitModelTest {
	
	private FruitModel fruit; 
	private SnakeModel snakeModel;
	
	@BeforeEach
	public void setup() {
		fruit = new FruitModel();
		snakeModel = new SnakeModel(5, 2);
		
	}
		
	@Test
	public void testFruitInSnake() {
		BoardModel boardModel = new BoardModel(50, 50, 10);
		BoardController boardController = new BoardController(boardModel);
		SnakeController snakeController = new SnakeController(snakeModel);
		
		fruit.setPositionX(3);
		fruit.setPositionY(0);
		
		boolean fruitIsInSnake = boardController.isFruitInSnake(snakeModel, fruit);
		assertEquals(fruitIsInSnake, true);
		
		fruit.setPositionX(5);
		fruit.setPositionY(1);
		
		boolean fruitIsNotInSnake = boardController.isFruitInSnake(snakeModel, fruit);
		assertEquals(fruitIsNotInSnake, false);
		
		snakeModel.setDirection(Dir.down);
		snakeController.move();
		
		boolean fruitIsInSnake2 = boardController.isFruitInSnake(snakeModel, fruit);
		assertEquals(fruitIsInSnake2, true);
		
		fruit.setPositionX(23);
		fruit.setPositionY(28);
		
		boolean fruitIsNotInSnake2 = boardController.isFruitInSnake(snakeModel, fruit);
		assertEquals(fruitIsNotInSnake2, false);	
	}
}