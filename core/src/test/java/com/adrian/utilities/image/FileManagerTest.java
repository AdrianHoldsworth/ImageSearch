package com.adrian.utilities.image;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;

import org.junit.Test;

import com.adrian.utilities.image.strategy.CountingStrategy;
import com.adrian.utilities.image.strategy.DisplayFileStrategy;
import com.adrian.utilities.image.strategy.FileHandlingStrategy;

public class FileManagerTest {

	@Test
	public void FileManagerTest_NoFilter_Correct_Number_Of_Files() throws IOException {
		Path directory = Paths.get("./src/test/resources");
		FileHandlingStrategy strategy = new DisplayFileStrategy(System.out);
		Predicate<? super Path> allFilesFilter =  (path) -> true; 
		FileManager manager = new FileManager();
		manager.process(directory, strategy, allFilesFilter);
	}
	
	@Test
	public void FileManagerTest_HiddenFileFilter_Correct_Number_Of_Files() throws IOException {
		Path directory = Paths.get("./src/test/resources");
		
		FileHandlingStrategy strategy = new CountingStrategy();
		Predicate<? super Path> pomFilter =  (p) -> p.getFileName().toString().equals("foo"); 
		FileManager manager = new FileManager();
		manager.process(directory, strategy, pomFilter);
		
		assertEquals(2, ((CountingStrategy)strategy).getCounter());

	}
}