package com.adrian.utilities.image.visitor;

import java.nio.file.Path;
import java.util.List;

public class ReportingPathVisitor implements PathVisitor {

	@Override
	public void visit(List<Path> paths) {
		paths.forEach(System.out::println);
		
	}

}
