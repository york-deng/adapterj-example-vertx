package com.adapterj.example.accelerator;

import com.adapterj.test.Testable;
import com.adapterj.widget.SimpleHTMLViewAccelerator;

/**
 * 
 * @author York/GuangYu DENG
 */
public class SimpleViewHTMLViewAccelerator1 extends SimpleHTMLViewAccelerator implements Testable {

	private static final long serialVersionUID = 4966741289199683847L;
	
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
