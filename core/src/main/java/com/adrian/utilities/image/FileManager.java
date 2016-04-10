package com.adrian.utilities.image;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.adrian.utilities.hasher.Sha1Hasher;
import com.adrian.utilities.image.deduplicator.DuplicateStrategy;
import com.adrian.utilities.image.dto.Hash;
import com.adrian.utilities.image.strategy.HashingStrategy;
import com.adrian.utilities.image.visitor.PathVisitor;

public abstract class FileManager {
	private final HashingStrategy hashingStrategy;
	
	public FileManager() {
		hashingStrategy = new HashingStrategy(new Sha1Hasher());
	}
	
	public void processDuplicates(Path path, Predicate<? super Path> filter) throws IOException {
		Map<String, List<Hash>> map = Files.walk(path, FileVisitOption.FOLLOW_LINKS)
				.filter(filter).map(hashingStrategy)
				.collect(Collectors.groupingBy(Hash::getHash))
				.entrySet().stream().filter(m -> m.getValue().size() > 1)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		map.entrySet().stream().forEach(f -> getPathVisitor().visit(f.getValue()));

	}
	
	public abstract PathVisitor getPathVisitor();

}
