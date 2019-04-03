package com.jayde.util.markdownutil.mdtool.builder;

import com.jayde.util.markdownutil.mdtool.Analyzer;
import com.jayde.util.markdownutil.mdtool.BlockType;
import com.jayde.util.markdownutil.mdtool.bean.Block;
import com.jayde.util.markdownutil.mdtool.bean.ValuePart;
import com.jayde.util.markdownutil.mdtool.tool.Tools;

import java.util.ArrayList;
import java.util.List;


public class CommonTextBuilder implements BlockBuilder {

    private String content;

    public CommonTextBuilder(String content) {
        this.content = content;
    }

    public Block bulid() {
        Block block = new Block();

        block.setType(BlockType.NONE);
        List<String> lines = Tools.read2List(content);
        List<ValuePart> valueParts = new ArrayList<>();
        for (String line : lines) {
            valueParts.addAll(Analyzer.analyzeLineText(line));
        }
        block.setValueParts(valueParts);
        block.contentText = content;
        return block;
    }

    public boolean isRightType() {
        return true;
    }

}
