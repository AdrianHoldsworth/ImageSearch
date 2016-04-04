package com.adrian.utilities.hasher;

import java.io.IOException;
import java.nio.file.Path;

/**
 * This just a wrapper for a given hashing strategy 
 * @author adrian
 *
 */
public interface Hasher {
	String hash(Path path) throws IOException;
}