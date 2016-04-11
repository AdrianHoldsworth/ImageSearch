package com.adrian.utilities.file.deduplicator;

import java.util.List;

import com.adrian.utilities.file.hash.dto.Hash;

public interface DuplicateStrategy {
	public void dedupe(List<Hash> paths);
}