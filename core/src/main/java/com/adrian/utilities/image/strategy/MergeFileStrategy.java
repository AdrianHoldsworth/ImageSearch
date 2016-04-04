package com.adrian.utilities.image.strategy;

import java.nio.file.Path;

import com.adrian.utilities.hasher.Hasher;

public class MergeFileStrategy implements FileHandlingStrategy {

	final Hasher hasher;
	public MergeFileStrategy(Hasher hasher) {
		this.hasher = hasher;
	}
	
	@Override
	public void handleFile(Path path) {
		// TODO Auto-generated method stub
		
	}

}
