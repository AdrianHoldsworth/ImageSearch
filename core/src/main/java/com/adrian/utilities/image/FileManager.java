package com.adrian.utilities.image;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.adrian.utilities.hasher.Sha1Hasher;
import com.adrian.utilities.image.dto.Hash;
import com.adrian.utilities.image.strategy.MergeFileStrategy;

public class FileManager {
		
	public void process(Path path, MergeFileStrategy strategy, Predicate<? super Path> filter) throws IOException {
		Map<String, List<Hash>> map  = Files.walk(path, FileVisitOption.FOLLOW_LINKS)
		.filter(filter).map(strategy).collect(Collectors.groupingBy(Hash::getHash));
		
		
		System.out.println("end");
		
	}

	public static void main(String[] args) throws IOException {
		new FileManager().process(Paths.get("./src/test/resources/directory/"), new MergeFileStrategy(new Sha1Hasher()), (p)-> !Files.isDirectory(p));
		System.out.println("end");
	}
}
