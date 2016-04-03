package com.adrian.utilities.image;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import com.adrian.utilities.image.strategy.FileHandlingStrategy;

public class FileManager {
		
	public void process(Path path, FileHandlingStrategy strategy, Predicate<? super Path> filter) throws IOException {
		Files.walk(path, FileVisitOption.FOLLOW_LINKS)
		.filter(filter).forEach(strategy::handleFile);
	}

}
