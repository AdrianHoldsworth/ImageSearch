package com.adrian.utilities.image.visitor;
import java.util.List;

import com.adrian.utilities.image.deduplicator.DuplicateStrategy;
import com.adrian.utilities.image.dto.Hash;
public class PathVisitor {

	final DuplicateStrategy duplicateStrategy;
	
	public PathVisitor(DuplicateStrategy duplicateStrategy) {
		this.duplicateStrategy = duplicateStrategy;
	}
	public void visit(List<Hash> hashedPaths) {
		duplicateStrategy.dedupe(hashedPaths);
	}
}
