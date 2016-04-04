package com.adrian.utilities.hasher;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class ShaHasherTest {

	@Test
	public void testHasherCreatesExpectedHash() throws IOException {
		Path path = Paths.get("./src/test/resources/directory/foo");
		Hasher hasher = new Sha1Hasher();
		assertThat(hasher.hash(path), equalTo("acbd18db4cc2f85cedef654fccc4a4d8"));
	}
}
