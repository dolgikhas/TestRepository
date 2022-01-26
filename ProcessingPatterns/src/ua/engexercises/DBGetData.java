package ua.engexercises;

import java.util.ArrayList;
import java.util.HashMap;

public class DBGetData {
	HashMap<String, ArrayList<String>> dataDB;

	public DBGetData(HashMap<String, ArrayList<String>> dataDB) {
		this.dataDB = dataDB;
	}
	
	public ArrayList<String> getListData( String key ) {
		return dataDB.get( key );
	}	
}
