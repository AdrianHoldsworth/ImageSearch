package com.adrian.utilities.image.strategy;

import com.adrian.utilities.image.visitor.PathVisitor;

public interface ReportingStrategy {

	public void accept (PathVisitor visitor); 
}
