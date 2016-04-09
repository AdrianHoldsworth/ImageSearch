package com.adrian.utilities.image.visitor;

import java.nio.file.Path;
import java.util.List;

public class ReportingDuplicateStrategy implements DuplicateStrategy {

	@Override
	public void dedupe(List<Path> paths) {
		if (paths.size() <= 1) {
			return;
		} else {
			for (Path path : paths) {
				action(path);
			}
		}

	}
	
	public void action(Path path) {
	}

}
