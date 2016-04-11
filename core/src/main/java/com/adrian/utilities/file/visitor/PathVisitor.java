package com.adrian.utilities.file.visitor;
import java.util.List;

import com.adrian.utilities.file.deduplicator.DuplicateStrategy;
import com.adrian.utilities.file.hash.dto.Hash;
public class PathVisitor {

	final DuplicateStrategy duplicateStrategy;
	
	public PathVisitor(DuplicateStrategy duplicateStrategy) {
		this.duplicateStrategy = duplicateStrategy;
	}
	public void visit(List<Hash> hashedPaths) {
		duplicateStrategy.dedupe(hashedPaths);
	}
}
