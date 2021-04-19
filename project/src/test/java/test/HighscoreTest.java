package test;

import controller.Highscore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



public class HighscoreTest {

	private Highscore user1;
	private Highscore user2;
	private Highscore user3;
	private Highscore user4;
	private Highscore user5;
	private List<Highscore> scores;
	private Collection<Highscore> expected;
	
	@BeforeEach
	public void setUp() {
		user1 = new Highscore("User1", 7, "19.04.2021");
		user2 = new Highscore("User2", 13, "18.04.2021");
		user3 = new Highscore("User3", 18, "19.04.2021");
		user4 = new Highscore("User4", 2, "16.04.2021");
		user5 = new Highscore("User5", 27, "13.04.2021");
		scores = new ArrayList<Highscore>(Arrays.asList(user1, user2, user3, user4, user5));
	}
	
	@Test
	@DisplayName("Check if highscores are sorted correctly, from low to high")
	void testNormal() throws Exception {
		expected = new ArrayList<Highscore>(Arrays.asList(user4, user1, user2, user3, user5));
		Collections.sort(scores);
		testHighscores(scores, expected);
		
	}
	
	private void testHighscores(Collection<Highscore> actualScores, Collection<Highscore> expectedScores) {
		Iterator<Highscore> actual = actualScores.iterator();
		Iterator<Highscore> expected = expectedScores.iterator();
		
		while(expected.hasNext()) {
			assertTrue(actual.hasNext());
			Highscore actualScore = actual.next();
			Highscore expectedScore = expected.next();
			assertEquals(expectedScore.getPoints(), actualScore.getPoints(),
								"Highscore should have been " + expectedScores + ", but was " + actualScores);
							
		}
	}
}
