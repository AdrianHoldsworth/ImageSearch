package com.adrian.utilities.file.deduplicator;

import java.nio.file.Path;
import java.util.List;

import com.adrian.utilities.file.hash.dto.Hash;

public class ReportingDuplicateStrategy implements DuplicateStrategy {

	@Override
	public void dedupe(List<Hash> paths) {
		if (paths.size() > 1) {
			System.out.println(paths.stream().map(Hash::getPath));
		}
	}

}