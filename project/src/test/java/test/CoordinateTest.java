package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Coordinate;

public class CoordinateTest {
	
private Coordinate coordinate;
	
	@BeforeEach
	public void setup() {
		coordinate = new Coordinate(5, 12);
	}
	
	@Test
	public void testConstructor() {
		assertEquals(coordinate.getX(), 5);
		assertEquals(coordinate.getY(), 12);
	}
	
	
	
}
