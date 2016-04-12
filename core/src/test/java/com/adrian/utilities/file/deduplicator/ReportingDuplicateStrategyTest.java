package com.adrian.utilities.file.deduplicator;

import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.easymock.EasyMock;
import org.junit.Test;

import com.adrian.utilities.file.hash.dto.Hash;

public class ReportingDuplicateStrategyTest {

	@Test
	public void testDedupeFunction_1_Hash() {
		PrintWriter mockPrintWriter = EasyMock.createMock(PrintWriter.class);
		ReportingDuplicateStrategy strategy = new ReportingDuplicateStrategy(mockPrintWriter);
		Path pathToFoo = Paths.get("./src/main/resources/directory/foo");
		
		Hash h = new Hash("foo", pathToFoo);
		List<Hash> hashes = new ArrayList<>();
		hashes.add(h);
		
		EasyMock.replay(mockPrintWriter);
		strategy.dedupe(hashes);
		EasyMock.verify(mockPrintWriter);
	}
	
	@Test
	public void testDedupeFunction_2_Hashes() {
		PrintWriter mockPrintWriter = EasyMock.createMock(PrintWriter.class);
		ReportingDuplicateStrategy strategy = new ReportingDuplicateStrategy(mockPrintWriter);
		Path pathToFoo = Paths.get("./src/main/resources/directory/foo");
		Path pathToSubFoo = Paths.get("./src/main/resources/directory/sub/foo");
		
		Hash h = new Hash("foo", pathToFoo);
		Hash h2 = new Hash("foo", pathToSubFoo);
		List<Hash> hashes = new ArrayList<>();
		hashes.add(h);
		hashes.add(h2);
		
		mockPrintWriter.println("Duplicate hash: foo - [./src/main/resources/directory/foo, ./src/main/resources/directory/sub/foo]");
		EasyMock.replay(mockPrintWriter);
		strategy.dedupe(hashes);
		EasyMock.verify(mockPrintWriter);
	}

}
