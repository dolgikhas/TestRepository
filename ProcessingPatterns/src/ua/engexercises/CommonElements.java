package ua.engexercises;

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
	
//	public static void main( String[] args ) throws Exception {
//		DBGetData dataDB;
//		RandomNumber randomNumber;
//		CommonElements commonElements;
//		String listWhoKey = "LIST_WHO";
//		String listWho1stValue = "I";
//		String listWhomKey = "LIST_WHOM";
//		String listWhom1stValue = "you";
//
//		HashMap<String, ArrayList<String>> mapListCommonElements =
//				new HashMap<>();
//		ArrayList<String> listWho = new ArrayList<>();
//		listWho.add( listWho1stValue );
//		mapListCommonElements.put( listWhoKey, listWho );
//		ArrayList<String> listWhom = new ArrayList<>();
//		listWhom.add( listWhom1stValue );
//		mapListCommonElements.put( listWhomKey, listWhom );
//		
//		dataDB = new DBGetData( mapListCommonElements );
//		randomNumber = new TestRandomNumber();
//		commonElements = new CommonElements( dataDB, randomNumber );
//		
//		System.out.println( commonElements.getRandomValue(listWhoKey) );
//
//	}

}
