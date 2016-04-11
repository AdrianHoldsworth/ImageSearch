package com.adrian.utilities.file.deduplicator;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import com.adrian.utilities.file.hash.dto.Hash;

public class ReportingDuplicateStrategy implements DuplicateStrategy {

	@Override
	public void dedupe(List<Hash> paths) {
		if (paths.size() > 1) {
			System.out.println("Duplicate hash: " + paths.stream().findFirst().map(Hash::getHash).get() + " - " + paths.stream().map(Hash::getPath).collect(Collectors.toList()));
		}
	}

}
