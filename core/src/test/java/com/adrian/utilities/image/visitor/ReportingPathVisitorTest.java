package com.adrian.utilities.image.visitor;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

public class ReportingPathVisitorTest {
	Path pathToFoo = Paths.get("./src/main/resources/directory/foo");
	Path pathToBar = Paths.get("./src/main/resources/directory/bar");
	Path pathToSubFoo = Paths.get("./src/main/resources/directory/sub/foo");
	
	@Test
	public void DeduperIsCalledForAVisitedList() {
		
		List<Path> paths = new ArrayList<>();
		paths.add(pathToBar);
		DuplicateStrategy mockStrategy = EasyMock.createMock(DuplicateStrategy.class);
		ReportingPathVisitor visitor = new ReportingPathVisitor(mockStrategy);
		
		
		mockStrategy.dedupe(EasyMock.isA(List.class));
		EasyMock.replay(mockStrategy);
		visitor.visit(paths);
		EasyMock.verify(mockStrategy);
	}
}
