package controller;

public interface FileHandlerInterface {
	
	public String getScoresFromFile(String filename);
	
	public void writeScoreToFile(BoardController boardController, String filename);
	
}	