package com.adrian.utilities.image.strategy;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import com.adrian.utilities.hasher.Hasher;
import com.adrian.utilities.hasher.Sha1Hasher;
import com.adrian.utilities.image.visitor.PathVisitor;
import com.adrian.utilities.image.visitor.ReportingPathVisitor;

public class MergeFileStrategyTest {

	@Test
	public void testMergeAggregatesFile() throws IOException {
		Hasher mockHasher = EasyMock.createMock(Sha1Hasher.class);
		PathVisitor mockVisitor = EasyMock.createMock(ReportingPathVisitor.class);
		Path pathToFoo = Paths.get("./src/main/resources/directory/foo");
		Path pathToBar = Paths.get("./src/main/resources/directory/bar");
		Path pathToSubFoo = Paths.get("./src/main/resources/directory/sub/foo");
		
		MergeFileStrategy strategy = new MergeFileStrategy(mockHasher);
		
		expect(mockHasher.hash(pathToFoo)).andReturn("foo");
		expect(mockHasher.hash(pathToBar)).andReturn("bar");
		expect(mockHasher.hash(pathToSubFoo)).andReturn("foo");
		
		EasyMock.replay(mockHasher);
		strategy.handleFile(pathToFoo);
		strategy.handleFile(pathToBar);
		strategy.handleFile(pathToSubFoo);
		EasyMock.verify(mockHasher);
		
		assertThat(strategy.map.get("foo").size(), equalTo(2));
		assertThat(strategy.map.get("bar").size(), equalTo(1));
		
	}
	
	@Test
	public void testPathVisitor() throws IOException {
		Hasher mockHasher = EasyMock.createMock(Sha1Hasher.class);
		PathVisitor mockVisitor = EasyMock.createMock(ReportingPathVisitor.class);
		Path pathToFoo = Paths.get("./src/main/resources/directory/foo");
		Path pathToBar = Paths.get("./src/main/resources/directory/bar");
		Path pathToSubFoo = Paths.get("./src/main/resources/directory/sub/foo");
		MergeFileStrategy strategy = new MergeFileStrategy(mockHasher);
		
		expect(mockHasher.hash(pathToFoo)).andReturn("foo");
		expect(mockHasher.hash(pathToBar)).andReturn("bar");
		expect(mockHasher.hash(pathToSubFoo)).andReturn("foo");
		
		List<Path> fooList = new ArrayList<>();
		fooList.add(pathToFoo);
		fooList.add(pathToSubFoo);
		
		List<Path> barList = new ArrayList<>();
		barList.add(pathToBar);
		
		mockVisitor.visit(barList);
		expectLastCall();
		
		mockVisitor.visit(fooList);
		expectLastCall();
		
		EasyMock.replay(mockHasher, mockVisitor);
		strategy.handleFile(pathToFoo);
		strategy.handleFile(pathToBar);
		strategy.handleFile(pathToSubFoo);
		strategy.accept(mockVisitor);
				
		EasyMock.verify(mockHasher, mockVisitor);
		
		assertThat(strategy.map.get("foo").size(), equalTo(2));
		assertThat(strategy.map.get("bar").size(), equalTo(1));
		
	}
	
}
