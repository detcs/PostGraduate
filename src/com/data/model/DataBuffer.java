package com.data.model;

import java.util.ArrayList;
import java.util.List;

import com.view.util.ViewGenerator;


public class DataBuffer<V extends ViewGenerator> {
	private final static int GAP = 5;
	private final static int ZONES = 3;
	private Object[][] generators;
	private Object[] generators1;
	private Object[] generators2;
	private Object[] generators3;

	public DataBuffer() {
		generators1 = new Object[GAP];
		generators2 = new Object[GAP];
		generators3 = new Object[GAP];
		generators = new Object[ZONES][GAP];
		generators[0] = generators1;
		generators[1] = generators2;
		generators[2] = generators3;
	}

	List<String> list = new ArrayList<String>();

	public int getCount() {
		return 10;
	}

	public ViewGenerator getDataItem(int index) {
		return null;
	}

	// private void pullData() {
	// }
}
