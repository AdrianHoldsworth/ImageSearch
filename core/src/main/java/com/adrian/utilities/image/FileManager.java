package com.adrian.utilities.image;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.adrian.utilities.image.strategy.FileHandlingStrategy;

public class FileManager {
	final Path path;
	final FileHandlingStrategy strategy;
	
	public FileManager(FileHandlingStrategy strategy, Path path) {
		this.strategy = strategy;
		this.path = path;
	}
	
	public void process() throws IOException {
		Files.walk(path, FileVisitOption.FOLLOW_LINKS).forEach(strategy::handleFile);
	}

}
