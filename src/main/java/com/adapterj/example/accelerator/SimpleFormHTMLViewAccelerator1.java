package com.adapterj.example.accelerator;

import com.adapterj.test.Testable;
import com.adapterj.widget.SimpleHTMLViewAccelerator;

/**
 * 
 * @author York/GuangYu DENG
 */
public class SimpleFormHTMLViewAccelerator1 extends SimpleHTMLViewAccelerator implements Testable {

	private static final long serialVersionUID = -7981386958676918730L;
	
	private String _test = "It's NOT working ...";
	
	// Testable methods
	
	@Override
	public void test(final String test) {
		_test = test;
	}
	
	@Override
	public String test() {
		return _test;
	}
}
