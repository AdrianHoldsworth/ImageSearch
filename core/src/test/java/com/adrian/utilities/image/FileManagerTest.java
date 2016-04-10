package com.adrian.utilities.image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;

import org.junit.Test;

public class FileManagerTest {

	@Test
	public void FileManagerTest_NoFilter_Correct_Number_Of_Files() throws IOException {
		Path directory = Paths.get("./src/test/resources");
		
		Predicate<? super Path> allFilesFilter =  (p) ->  !Files.isDirectory(p); 
		FileManager manager = new ReportingFileManager();
		manager.processDuplicates(directory, allFilesFilter);
	}
	
	@Test
	public void FileManagerTest_HiddenFileFilter_Correct_Number_Of_Files() throws IOException {
		Path directory = Paths.get("./src/test/resources");
		
		Predicate<? super Path> pomFilter =  (p) -> p.getFileName().toString().equals("foo"); 
		FileManager manager = new ReportingFileManager();
		manager.processDuplicates(directory, pomFilter);
		

	}
}