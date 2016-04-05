package com.adrian.utilities.image.strategy;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.adrian.utilities.hasher.Hasher;
import com.adrian.utilities.image.visitor.PathVisitor;
/**
 * Provide an mechanism to store and aggregate hashes with O(1) complexity
 *  
 * @author adrian
 *
 */
public class MergeFileStrategy implements FileHandlingStrategy, ReportingStrategy {

	final Hasher hasher;
	Map<String, List<Path>> map = new HashMap<>();
	public MergeFileStrategy(Hasher hasher) {
		this.hasher = hasher;
	}
	
	@Override
	public void handleFile(Path path) {
		String hash = null;
		try {
			hash = hasher.hash(path);
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

	
}