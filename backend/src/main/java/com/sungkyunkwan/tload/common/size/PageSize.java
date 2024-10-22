package com.sungkyunkwan.tload.common.size;

public enum PageSize {
	BOARD(10),
	REVIEW(20);

	private final int size;

	PageSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}
}
