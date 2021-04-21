package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controller.SnakeController;
import model.BoardModel;
import model.SnakeModel;
import utils.Dir;


public class SnakeModelTest {
	
	private SnakeModel snakemodel;
	
	@BeforeEach
	public void setup() {
		snakemodel = new SnakeModel(5, 2);
		
	}
	
	@Test
	public void testConstructor() {
		assertEquals(snakemodel.getInitSize(), 5);
		assertEquals(snakemodel.getSpeed(), 2);
	}
	
	@Test
	public void testSnakeCrashed() {
		SnakeController snakeController = new SnakeController(snakemodel);
		BoardModel boardModel = new BoardModel(50, 50, 10);
		snakeController.move();
		boolean notCrashed = snakeController.snakeCrashed(boardModel);
		assertEquals(notCrashed, false);
		snakemodel.setDirection(Dir.up);
		snakeController.move();
		boolean crashed = snakeController.snakeCrashed(boardModel);
		assertEquals(crashed, true);
		
		//TODO More edge-cases
		
	}

}
