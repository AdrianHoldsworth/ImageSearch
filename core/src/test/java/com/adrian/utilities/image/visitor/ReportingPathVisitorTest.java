package com.adrian.utilities.image.visitor;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import com.adrian.utilities.image.deduplicator.DuplicateStrategy;
import com.adrian.utilities.image.dto.Hash;

public class ReportingPathVisitorTest {
	Path pathToFoo = Paths.get("./src/main/resources/directory/foo");
	Path pathToBar = Paths.get("./src/main/resources/directory/bar");
	Path pathToSubFoo = Paths.get("./src/main/resources/directory/sub/foo");
	
	@Test
	public void DeduperIsCalledForAVisitedList() {
		
		List<Hash> paths = new ArrayList<>();
		paths.add(new Hash("bar", pathToBar));
		DuplicateStrategy mockStrategy = EasyMock.createMock(DuplicateStrategy.class);
		PathVisitor visitor = new PathVisitor(mockStrategy);
		
		
		mockStrategy.dedupe(EasyMock.isA(List.class));
		EasyMock.replay(mockStrategy);
		visitor.visit(paths);
		EasyMock.verify(mockStrategy);
	}
}
