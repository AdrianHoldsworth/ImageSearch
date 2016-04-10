package com.adrian.utilities.image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.adrian.utilities.image.deduplicator.ReportingDuplicateStrategy;
import com.adrian.utilities.image.visitor.PathVisitor;

public class ReportingFileManager extends FileManager {

	private final PathVisitor pathVisitor = new PathVisitor(new ReportingDuplicateStrategy());
	
	@Override
	public PathVisitor getPathVisitor() {
		return pathVisitor;
	}
	
	public static void main(String[] args) throws IOException {
		new ReportingFileManager().processDuplicates(Paths.get("./src/test/resources/directory/"),
											(p)-> !Files.isDirectory(p));
		System.out.println("end");
	}

}
