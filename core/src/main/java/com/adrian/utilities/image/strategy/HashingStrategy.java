package com.adrian.utilities.image.strategy;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.adrian.utilities.hasher.Hasher;
import com.adrian.utilities.image.dto.Hash;

/**
 * Provide an mechanism to store and aggregate hashes with O(1) complexity
 * 
 * @author adrian
 *
 */
public class HashingStrategy implements Function<Path, Hash> {

	final Hasher hasher;
	Map<String, List<Path>> map = new HashMap<>();

	public HashingStrategy(Hasher hasher) {
		this.hasher = hasher;
	}

	@Override
	public Hash apply(Path t) {
		String hash = null;
		try {
			hash = hasher.hash(t);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return new Hash(hash, t);
	}
}