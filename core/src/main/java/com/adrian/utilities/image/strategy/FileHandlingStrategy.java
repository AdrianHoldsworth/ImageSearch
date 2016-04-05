package com.adrian.utilities.image.strategy;

import java.io.IOException;
import java.nio.file.Path;

public interface FileHandlingStrategy {

	public void handleFile (Path path); 
}
