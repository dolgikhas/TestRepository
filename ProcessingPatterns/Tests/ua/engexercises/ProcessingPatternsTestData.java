package ua.engexercises;

import java.util.ArrayList;
import java.util.HashMap;

import ua.engexercises.model.CommonElements;
import ua.engexercises.model.DBGetData;
import ua.engexercises.model.RandomNumber;

public class ProcessingPatternsTestData {
	public final String listWhoKey = "LIST_WHO";
	public final String listWho1stValue = "I";
	public final String listWhomKey = "LIST_WHOM";
	public final String listWhom1stValue = "you";
	public final String patternTaskSeeSimple = "see #" + listWhoKey + "#/#" + listWhomKey + "#";
	public final String patternAnswerSeeSimple = "#" + listWhoKey + "# see #" + listWhomKey + "#.";

	public DBGetData dataDB;
	public RandomNumber randomNumber;
	public CommonElements commonElements;

	public ProcessingPatternsTestData() {
		HashMap<String, ArrayList<String>> mapListCommonElements = new HashMap<>();
		
		ArrayList<String> listWho = new ArrayList<>();
		listWho.add( listWho1stValue );
		mapListCommonElements.put( listWhoKey, listWho );
		
		ArrayList<String> listWhom = new ArrayList<>();
		listWhom.add( listWhom1stValue );
		mapListCommonElements.put( listWhomKey, listWhom );
		
		dataDB = new DBGetData( mapListCommonElements );
		randomNumber = new TestRandomNumber();
		commonElements = new CommonElements( dataDB, randomNumber );
	}
}