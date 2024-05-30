package ua.training;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;

public class TestClass {

	public static void main(String[] args) {
		String examplesPath = "D:\\ENGLISH. FULL\\WORDS\\examples\\stamp.txt";
		String examplesGooglePath = "G:\\Мій диск1\\ПЕРЕНОС\\ENGLISH\\stamp.txt";		
		
		try {
			File sourceFile = new File(examplesPath);
			File destinationFile = new File(examplesGooglePath);
		    Files.copy(sourceFile.toPath(), destinationFile.toPath());
			System.out.println("Files copied correct!");
		} catch (FileAlreadyExistsException e) {
			e.printStackTrace();
		} catch (NoSuchFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
