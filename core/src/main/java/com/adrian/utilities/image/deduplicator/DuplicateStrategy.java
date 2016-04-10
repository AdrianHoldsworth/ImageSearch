package com.adrian.utilities.image.deduplicator;

import java.util.List;

import com.adrian.utilities.image.dto.Hash;

public interface DuplicateStrategy {
	public void dedupe(List<Hash> paths);
}