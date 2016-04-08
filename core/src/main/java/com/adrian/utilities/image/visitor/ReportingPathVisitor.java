package com.adrian.utilities.image.visitor;

import java.nio.file.Path;
import java.util.List;

public class ReportingPathVisitor implements PathVisitor {

	final DuplicateStrategy duplicateStrategy;
	
	public ReportingPathVisitor(DuplicateStrategy duplicateStrategy) {
		this.duplicateStrategy = duplicateStrategy;
	}
	
	@Override
	public void visit(List<Path> paths) {
		duplicateStrategy.dedupe(paths);
	}
}
