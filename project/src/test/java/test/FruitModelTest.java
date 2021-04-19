package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.FruitModel;

public class FruitModelTest {
	
	private FruitModel fruit; 
	
	@BeforeEach
	public void setup() {
		fruit = new FruitModel();
	}
	
//	@Test
//	public void testConstructor() {
//		//fruit.getPositionX();
//		//assertEquals(fruit.getPositionX(), fruit.generateRandomPositionX());
//	}
	
	@Test
	public void testGenerateRandomPosition(){
		//assertEquals(fruit.generateRandomPositionX(), fruit.getPositionX());
//		int posX1 = fruit.getPositionX();
//		int posX2 = fruit.getPositionX();
//		System.out.println(posX1);
//		System.out.println(posX2);
		
	}
	
	
}
