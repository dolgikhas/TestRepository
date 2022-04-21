package ua.engexercises.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ExercisesModel {
	DBGetData dbData;
	ArrayList<String> listThemes;
	
	public ExercisesModel() {
		dbData = new DBGetData();
		listThemes = null;
	}

	public ArrayList<String> getListThemes() throws FileNotFoundException, IOException {
		if ( null == listThemes )
			listThemes = dbData.getListThemes();
		
		return listThemes;
	}

	public int getThemesNumber() {
		return listThemes.size();
	}
	
	public String getRandomPattern( String theme ) {
		return dbData.getRandomPattern( theme );
	}
	
	public String getThemeByIndex( int index ) {
		return listThemes.get(index);
	}

	public String getRandomTheme() {
		return listThemes.get( RandomNumber.getRandomNumber( listThemes.size() ) );
	}

}
