package com.adrian.utilities.image.visitor;

import java.nio.file.Path;

public interface DuplicateStrategy {
	public void dedupe(Path path);
}