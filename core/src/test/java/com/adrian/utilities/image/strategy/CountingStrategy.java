package com.adrian.utilities.image.strategy;

import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

public class CountingStrategy implements FileHandlingStrategy{

	private AtomicInteger counter = new AtomicInteger();
	@Override
	public void handleFile(Path path) {
//		System.out.println(path.getFileName());
		counter.incrementAndGet();
	}
	
	public int getCounter() {
		return counter.intValue();
	}
}
