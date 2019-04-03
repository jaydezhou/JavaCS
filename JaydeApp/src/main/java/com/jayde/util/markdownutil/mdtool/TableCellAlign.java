package com.jayde.util.markdownutil.mdtool;

public enum TableCellAlign {
	LEFT("left"), CENTER("center"), RIGHT("right"), NONE("");
	
	private String value;
	private TableCellAlign(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
}
