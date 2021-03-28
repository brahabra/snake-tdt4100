package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Highscore implements Comparable<Highscore>{

	private String username;
	private int points;
	private String date;

	
	
	public Highscore(String username, int points, String date) {
		this.username = username;
		this.points = points;
		this.date = date;
	}

	public int getPoints() {
		return points;
	}
	
	public static String padRight(String s, int n) {
	     return String.format("%-" + n + "s", s);  
	}

	public static String padLeft(String s, int n) {
	    return String.format("%" + n + "s", s);  
	}
	
	public String formatSpaces(String s) {
		int spaces = 15;
		int a = spaces - s.length();
		
		String tom = " ";
		//s += s.repeat(15);
		s += tom.repeat(a);
		s += ".";
		return s;
	}
	
	@Override
	public int compareTo(Highscore o) {
		return Integer.compare(this.points, o.points);
	}
	
	@Override
	public String toString() {
		return padRight(". ", 6) +  this.points + padRight(" ", 7) + padRight(this.username, 16) + this.date; // + padLeft(this.date, 20);
		//return this.points + padRight(" ", 10)+ formatSpaces(username);
	}

}

