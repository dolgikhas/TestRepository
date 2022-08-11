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

	public static HashMap<String, String> getMapItems(String filePath) throws FileNotFoundException, IOException {
		ArrayList<String> listMapItems = getListItems(filePath);
		HashMap<String, String> mapItems = new HashMap<String, String>();
		for (String mapItemsStr : listMapItems) {
			String[] arrMapItems = mapItemsStr.split("\t");
			String mapItem0 = arrMapItems[ 0 ];
			String mapItem1 = arrMapItems[ 1 ];
			
			mapItems.put(mapItem0, mapItem1);
		}
			
		return mapItems;
	}
}