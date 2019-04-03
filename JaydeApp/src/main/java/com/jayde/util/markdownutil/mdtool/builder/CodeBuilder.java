package com.jayde.util.markdownutil.mdtool.builder;

import com.jayde.util.markdownutil.mdtool.BlockType;
import com.jayde.util.markdownutil.mdtool.bean.Block;
import com.jayde.util.markdownutil.mdtool.bean.ValuePart;

public class CodeBuilder implements BlockBuilder{

	private String content;
	public CodeBuilder(String content){
		this.content = content;
	}
	
	public Block bulid() {
		Block block = new Block();
		block.setType(BlockType.CODE);
		block.setValueParts(new ValuePart(content));
		block.contentText = content;
		return block;
	}

	public boolean isRightType() {
		return false;
	}

}
