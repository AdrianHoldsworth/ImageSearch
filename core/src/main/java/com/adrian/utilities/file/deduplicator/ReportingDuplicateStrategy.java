package com.adrian.utilities.file.deduplicator;

import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import com.adrian.utilities.file.hash.dto.Hash;

public class ReportingDuplicateStrategy implements DuplicateStrategy {

	final PrintWriter printWriter;
	
	public ReportingDuplicateStrategy(PrintWriter printWriter) {
		this.printWriter = printWriter;
	}
	
	@Override
	public void dedupe(List<Hash> paths) {
		if (paths.size() > 1) {
			printWriter.println("Duplicate hash: " + paths.stream().findFirst().map(Hash::getHash).get() + " - " + paths.stream().map(Hash::getPath).collect(Collectors.toList()));
		} else {
			;//nop
		}
	}

}
