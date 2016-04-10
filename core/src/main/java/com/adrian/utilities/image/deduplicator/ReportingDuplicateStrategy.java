package com.adrian.utilities.image.deduplicator;

import java.nio.file.Path;
import java.util.List;

import com.adrian.utilities.image.dto.Hash;

public class ReportingDuplicateStrategy implements DuplicateStrategy {

	@Override
	public void dedupe(List<Hash> paths) {
		if (paths.size() > 1) {
			System.out.println(paths.stream().map(Hash::getPath));
		}
	}

}
