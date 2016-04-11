package com.adrian.utilities.file.hash;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * This just a wrapper for a given hashing strategy 
 * @author adrian
 *
 */
public class Sha1Hasher implements Hasher {
	public String hash(Path path) throws IOException {
		FileInputStream fis = new FileInputStream(path.toFile());
		String md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(fis);
		fis.close();
		return md5;
	}
}
