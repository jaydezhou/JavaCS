package com.jayde.util.markdownutil.mdtool.export;


import com.jayde.util.markdownutil.mdtool.bean.Block;

import java.util.List;

public interface Decorator {
	
	public void beginWork(String outputFilePath);
	
	public void decorate(List<Block> list);
	
	public void afterWork(String outputFilePath);
	
}
