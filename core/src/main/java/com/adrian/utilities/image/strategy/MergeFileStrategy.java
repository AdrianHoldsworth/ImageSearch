package com.adrian.utilities.image.strategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;

import com.adrian.utilities.hasher.Hasher;
import com.adrian.utilities.image.visitor.PathVisitor;
/**
 * Provide an mechanism to store and aggregate hashes with O(1) complexity
 *  
 * @author adrian
 *
 */
public class MergeFileStrategy implements FileHandlingStrategy, ReportingStrategy, Function<Path, Map<String, Path>> {

	final Hasher hasher;
	Map<String, List<Path>> map = new HashMap<>();
	public MergeFileStrategy(Hasher hasher) {
		this.hasher = hasher;
	}
	
	@Override
	public void handleFile(Path path) {
//		System.out.println(path);
		String hash = null;
		try {
			if ( !Files.isDirectory(path)) {
				hash = hasher.hash(path);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Path> paths = map.get(hash);
		if (paths == null) {
			paths = new ArrayList<Path>();
			map.put(hash, paths);
		}
		paths.add(path);
	}

	@Override
	public void accept(PathVisitor visitor) {
		Set<Entry<String, List<Path>>> entrySet = map.entrySet();
		for (Entry<String, List<Path>> entry : entrySet) {
			visitor.visit(entry.getValue());
		}
	}

	@Override
	public Map<String, Path> apply(Path t) {
		String hash = null;
		Map <String, Path> map = new HashMap<>();
		try {
			hash =  hasher.hash(t);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		map.put(hash,t);
		return map;
	}
	
	
}