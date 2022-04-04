package ua.engexercises.model;

import java.util.ArrayList;
import java.util.HashMap;

public class CommonElements {
	DBGetData dataDB;
	RandomNumber randomNumbers;
	HashMap<String, ArrayList<String>> commonElements;


	public CommonElements(DBGetData dataDB, RandomNumber randomNumbers) {
		this.dataDB = dataDB;
		this.randomNumbers = randomNumbers;
		commonElements = new HashMap<>();
	}

	public String getRandomValue(String key) throws Exception {
		if ( false == commonElements.containsKey(key) )
			commonElements.put( key, dataDB.getListData(key) );
		
		ArrayList<String> listElements = commonElements.get( key );
		int amountElements = listElements.size();

		return listElements.get(
					randomNumbers.getRandomNumber( amountElements ) );
	}
	
}
