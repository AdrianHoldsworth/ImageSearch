package com.adrian.utilities.image.dto;

import java.nio.file.Path;

public class Hash {

	private final String hash;
	private final Path path;
	
	public Hash(String hash, Path path) {
		this.hash = hash;
		this.path = path;
	}

	public String getHash() {
		return hash;
	}

	public Path getPath() {
		return path;
	}
}
