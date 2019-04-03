package com.jayde.util.markdownutil.mdtool.bean;

import com.jayde.util.markdownutil.mdtool.BlockType;
import com.jayde.util.markdownutil.mdtool.TableCellAlign;

import java.util.Arrays;
import java.util.List;


public class Block {
    public String contentText = "";
    private String mdToken;
    private BlockType type;
    private ValuePart[] valueParts;
    private int level;
    private List<List<Block>> tableData;
    private List<Block> listData;
    private Block lineData;
    private TableCellAlign align;
    private String language;

    public String getMdToken() {
        return mdToken;
    }

    public void setMdToken(String mdToken) {
        this.mdToken = mdToken;
    }

    public BlockType getType() {
        return type;
    }

    public void setType(BlockType type) {
        this.type = type;
    }

    public ValuePart[] getValueParts() {
        return valueParts;
    }

    public void setValueParts(ValuePart... valueParts) {
        this.valueParts = valueParts;
    }

    public void setValueParts(List<ValuePart> parts) {
        this.valueParts = new ValuePart[parts.size()];
        for (int i = 0, l = parts.size(); i < l; i++) {
            this.valueParts[i] = parts.get(i);
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<List<Block>> getTableData() {
        return tableData;
    }

    public void setTableData(List<List<Block>> tableData) {
        this.tableData = tableData;
    }

    public List<Block> getListData() {
        return listData;
    }

    public void setListData(List<Block> listData) {
        this.listData = listData;
    }

    public Block getLineData() {
        return lineData;
    }

    public void setLineData(Block lineData) {
        this.lineData = lineData;
    }

    public TableCellAlign getAlign() {
        return align;
    }

    public void setAlign(TableCellAlign align) {
        this.align = align;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "mdToken:" + mdToken + "blockType:" + type + "|valueParts:" + Arrays.toString(valueParts) + "|level:" + level + "|listData:" + listData;
    }
}
