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
	
	@Override
	public int compareTo(Highscore o) {
		return Integer.compare(this.points, o.points);
	}
	
	@Override
	public String toString() {
	return this.points + padRight(" ", 20) + padRight(this.username, 30) + padRight(this.date, 10); // + padLeft(this.date, 20);
	//return this.points + "		 	" + this.username+ "			" + this.date;
	}
	
	public static void main(String[] args) {

	}	
}

