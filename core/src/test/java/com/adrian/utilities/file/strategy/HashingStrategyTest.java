package com.adrian.utilities.file.strategy;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import com.adrian.utilities.file.hash.Hasher;
import com.adrian.utilities.file.hash.HashingStrategy;
import com.adrian.utilities.file.hash.Sha1Hasher;
import com.adrian.utilities.file.hash.dto.Hash;
import com.adrian.utilities.file.visitor.PathVisitor;


public class HashingStrategyTest {

	@Test
	public void testMergeAggregatesFile() throws IOException {
		Hasher mockHasher = EasyMock.createMock(Sha1Hasher.class);
		PathVisitor mockVisitor = EasyMock.createMock(PathVisitor.class);
		Path pathToFoo = Paths.get("./src/main/resources/directory/foo");
		Path pathToBar = Paths.get("./src/main/resources/directory/bar");
		Path pathToSubFoo = Paths.get("./src/main/resources/directory/sub/foo");
		
		HashingStrategy strategy = new HashingStrategy(mockHasher);
		
		expect(mockHasher.hash(pathToFoo)).andReturn("foo");
		expect(mockHasher.hash(pathToBar)).andReturn("bar");
		expect(mockHasher.hash(pathToSubFoo)).andReturn("foo");
		
		EasyMock.replay(mockHasher);
		strategy.apply(pathToFoo);
		strategy.apply(pathToBar);
		strategy.apply(pathToSubFoo);
		EasyMock.verify(mockHasher);
		
//		assertThat(strategy.map.get("foo").size(), equalTo(2));
//		assertThat(strategy.map.get("bar").size(), equalTo(1));
		
	}
	
	@Test (expected=RuntimeException.class)
	public void testMergeAggregatesFile_ThrowsException() throws IOException {
		Hasher mockHasher = EasyMock.createMock(Sha1Hasher.class);
		PathVisitor mockVisitor = EasyMock.createMock(PathVisitor.class);
		Path pathToFoo = Paths.get("./src/main/resources/directory/foo");
		Path pathToBar = Paths.get("./src/main/resources/directory/bar");
		Path pathToSubFoo = Paths.get("./src/main/resources/directory/sub/foo");
		
		HashingStrategy strategy = new HashingStrategy(mockHasher);
		
		expect(mockHasher.hash(pathToFoo)).andReturn("foo");
		expect(mockHasher.hash(pathToBar)).andReturn("bar");
		expect(mockHasher.hash(pathToSubFoo)).andThrow(new FileNotFoundException());
		
		EasyMock.replay(mockHasher);
		strategy.apply(pathToFoo);
		strategy.apply(pathToBar);
		strategy.apply(pathToSubFoo);
		EasyMock.verify(mockHasher);
		
	}
	
	@Test
	public void testPathVisitor() throws IOException {
		Hasher mockHasher = EasyMock.createMock(Sha1Hasher.class);
		PathVisitor mockVisitor = EasyMock.createMock(PathVisitor.class);
		Path pathToFoo = Paths.get("./src/main/resources/directory/foo");
		Path pathToBar = Paths.get("./src/main/resources/directory/bar");
		Path pathToSubFoo = Paths.get("./src/main/resources/directory/sub/foo");
		HashingStrategy strategy = new HashingStrategy(mockHasher);
		
		expect(mockHasher.hash(pathToFoo)).andReturn("foo");
		expect(mockHasher.hash(pathToBar)).andReturn("bar");
		expect(mockHasher.hash(pathToSubFoo)).andReturn("foo");
		
		List<Hash> fooList = new ArrayList<>();
		fooList.add(new Hash("foo", pathToFoo));
		fooList.add(new Hash("foo", pathToSubFoo));
		
		List<Hash> barList = new ArrayList<>();
		barList.add(new Hash("bar", pathToBar));
		
//		mockVisitor.visit(barList);
//		expectLastCall();
		
//		mockVisitor.visit(fooList);
//		expectLastCall();
		
		EasyMock.replay(mockHasher, mockVisitor);
		strategy.apply(pathToFoo);
		strategy.apply(pathToBar);
		strategy.apply(pathToSubFoo);
				
		EasyMock.verify(mockHasher, mockVisitor);
		
//		assertThat(strategy.map.get("foo").size(), equalTo(2));
//		assertThat(strategy.map.get("bar").size(), equalTo(1));
		
	}
	
}
