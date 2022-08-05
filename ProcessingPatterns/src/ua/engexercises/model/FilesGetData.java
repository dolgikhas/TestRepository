package ua.engexercises.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FilesGetData {
	public HashMap<String, ArrayList<String>> dataDB;
	
	public static ArrayList<String> getListItems( String fileName ) throws FileNotFoundException, IOException {
		ArrayList<String> listItems = new ArrayList<>();
		
		try ( BufferedReader br	= new BufferedReader(
				new FileReader( fileName ) ) ) {
			String strLine;
			while ( ( strLine = br.readLine() ) != null )
				listItems.add( strLine );			
		}
		
		return listItems;
	}
}