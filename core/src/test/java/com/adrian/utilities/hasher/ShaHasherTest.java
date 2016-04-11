package com.adrian.utilities.hasher;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import com.adrian.utilities.file.hash.Hasher;
import com.adrian.utilities.file.hash.Sha1Hasher;

public class ShaHasherTest {

	@Test
	public void testAFoo_ExpectAFooHash() throws IOException {
		Path path = Paths.get("./src/test/resources/directory/foo");
		Hasher hasher = new Sha1Hasher();
		assertThat(hasher.hash(path), equalTo("acbd18db4cc2f85cedef654fccc4a4d8"));
	}
	
	@Test
	public void testABar_DontExpectAFooHash() throws IOException {
		Path path = Paths.get("./src/test/resources/directory/bar");
		Hasher hasher = new Sha1Hasher();
		assertThat(hasher.hash(path), not(equalTo("acbd18db4cc2f85cedef654fccc4a4d8")));
	}
}
