package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.SnakeModel;


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

}
