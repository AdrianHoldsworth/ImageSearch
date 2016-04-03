package com.adrian.utilities.image.strategy;

import java.io.PrintStream;
import java.nio.file.Path;

public class DisplayFileStrategy implements FileHandlingStrategy {

	final PrintStream stream;
	public DisplayFileStrategy(PrintStream stream) {
		this.stream = stream;
	}
	@Override
	public void handleFile(Path path) {
		stream.print(path.getFileName());
	}

}
