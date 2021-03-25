package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Highscore implements Comparable<Highscore>{

	private String username;
	private int points;
	
	public Highscore(String username, int points) {
		this.username = username;
		this.points = points;
	}

	public int getPoints() {
		return points;
	}
	
	@Override
	public int compareTo(Highscore o) {
		return Integer.compare(this.points, o.points);
		
		//return Integer.compare(this.getSuitValue(this.getSuit()), o.getSuitValue(o.getSuit()));
	}
	
	
	@Override
	public String toString() {
		return this.username + this.points;
	}
	
	public static void main(String[] args) {
		Highscore a = new Highscore("a", 7);
		Highscore b = new Highscore("b", 10);
		Highscore c = new Highscore("c", 18);
		Highscore d = new Highscore("d", 3);
		Highscore e = new Highscore("e", 5);
		Highscore f = new Highscore("f", 23);
		Highscore g = new Highscore("g", 1);
		
		
		List<Highscore> allScores = new ArrayList<Highscore>();
		allScores.add(a);
		allScores.add(b);
		allScores.add(c);
		allScores.add(d);
		allScores.add(e);
		allScores.add(f);
		allScores.add(g);
		
//		System.out.println(første.getPoints());
//		System.out.println(første.compareTo(andre));
		System.out.println(allScores);
		Collections.sort(allScores);
		System.out.println(allScores);
		System.out.println(allScores.get(allScores.size()-1)); 
		List<Highscore> allScoresThreeBest = allScores.subList(allScores.size() - 3, allScores.size());
		System.out.println(allScoresThreeBest);
	}



	
	
}

