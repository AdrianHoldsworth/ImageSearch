package com.adrian.utilities.image;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class FileManagerTest {

	@Test
	public void FileManagerTest() {
		Path p = Paths.get("/");
		FileManager manager = new FileManager(p);

	}
}