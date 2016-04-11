package com.adrian.utilities.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

import org.easymock.EasyMock;
import org.junit.Test;

import com.adrian.utilities.file.FileManager;
import com.adrian.utilities.file.visitor.PathVisitor;

public class FileManagerTest {
	PathVisitor mockPathVisitor;
	@Test
	public void FileManagerTest_NoFilter_Correct_Number_Of_Files() throws IOException {
		Path directory = Paths.get("./src/test/resources");
		
		Predicate<? super Path> allFilesFilter =  (p) ->  !Files.isDirectory(p); 
		mockPathVisitor = EasyMock.createMock(PathVisitor.class);
		FileManager manager = new FileManager(mockPathVisitor);
		
		mockPathVisitor.visit(EasyMock.isA(List.class));
		EasyMock.expectLastCall().times(2);
				
		EasyMock.replay(mockPathVisitor);
		manager.processDuplicates(directory, allFilesFilter);
		EasyMock.verify(mockPathVisitor);
	}
	
	@Test
	public void FileManagerTest_HiddenFileFilter_Correct_Number_Of_Files() throws IOException {
		Path directory = Paths.get("./src/test/resources");
		
		Predicate<? super Path> pomFilter =  (p) -> p.getFileName().toString().equals("foo"); 
		mockPathVisitor = EasyMock.createMock(PathVisitor.class);
		FileManager manager = new FileManager(mockPathVisitor);
		
		mockPathVisitor.visit(EasyMock.isA(List.class));
		EasyMock.expectLastCall().times(1);
		EasyMock.replay(mockPathVisitor);
		manager.processDuplicates(directory, pomFilter);
		EasyMock.verify(mockPathVisitor);
		

	}
}