package com.adrian.utilities.image.visitor;
import java.nio.file.Path;
import java.util.List;
public interface PathVisitor {

	public void visit(List<Path> paths);
}
