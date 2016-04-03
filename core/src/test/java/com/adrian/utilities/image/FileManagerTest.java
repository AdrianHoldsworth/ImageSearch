package com.adrian.utilities.image;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import org.junit.Test;

import com.adrian.utilities.image.strategy.DisplayFileStrategy;
import com.adrian.utilities.image.strategy.FileHandlingStrategy;

public class FileManagerTest {

	@Test
	public void FileManagerTest_WithDisplayStrategy() throws IOException {
		Path p = Paths.get(".");
		FileHandlingStrategy strategy = new DisplayFileStrategy(System.out);
		FileManager manager = new FileManager(strategy, p);
		manager.process();

	}
}