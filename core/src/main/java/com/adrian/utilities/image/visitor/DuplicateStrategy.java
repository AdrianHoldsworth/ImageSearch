package com.adrian.utilities.image.visitor;

import java.nio.file.Path;
import java.util.List;

public interface DuplicateStrategy {
	public void dedupe(List<Path> paths);
}