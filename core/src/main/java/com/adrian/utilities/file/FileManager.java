package com.adrian.utilities.file;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.adrian.utilities.file.hash.HashingStrategy;
import com.adrian.utilities.file.hash.Sha1Hasher;
import com.adrian.utilities.file.hash.dto.Hash;
import com.adrian.utilities.file.visitor.PathVisitor;

public class FileManager {
	private final HashingStrategy hashingStrategy;
	private final PathVisitor pathVisitor;
	
	public FileManager(PathVisitor pathVisitor) {
		this.pathVisitor = pathVisitor;
		hashingStrategy = new HashingStrategy(new Sha1Hasher());
	}
	
	public void processDuplicates(Path path, Predicate<? super Path> filter) throws IOException {
		Map<String, List<Hash>> map = Files.walk(path, FileVisitOption.FOLLOW_LINKS)
				.filter(filter).map(hashingStrategy)
				.collect(Collectors.groupingBy(Hash::getHash))
				.entrySet().stream().filter(m -> m.getValue().size() > 1)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		map.entrySet().stream().forEach(f -> pathVisitor.visit(f.getValue()));

	}
	
	public static void main(String[] args) throws IOException {
//		new FileManager().processDuplicates(Paths.get("./src/test/resources/directory/"),
//											(p)-> !Files.isDirectory(p));
		System.out.println("end");
	}

}
