package ua.engexercises.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FilesGetData {
	public HashMap<String, ArrayList<String>> dataDB;
	
	public static ArrayList<String> getListItems() throws FileNotFoundException, IOException {
		ArrayList<String> listItems = new ArrayList<>();
		
		try ( BufferedReader br	= new BufferedReader(
				new FileReader( DBGetDataConstants.FILE_LIST_THEMES ) ) ) {
			String strLine;
			while ( ( strLine = br.readLine() ) != null )
				listItems.add( strLine );			
		}
		
		return listItems;
	}

	public static String getRandomPattern(String theme) {
		// get list themes
		
		
		// get random theme from list
		
		
		return null;
	}
}