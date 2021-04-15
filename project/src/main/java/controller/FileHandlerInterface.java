package controller;

import java.io.File;
import java.io.IOException;

public interface FileHandlerInterface {
	
	
	public String getScoresFromFile(File filename);
	
	public void writeScoreToFile();
	
}	
