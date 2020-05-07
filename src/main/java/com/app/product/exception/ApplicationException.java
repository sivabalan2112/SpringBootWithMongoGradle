package com.app.product.exception;

public class ApplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7839942868005140356L;

	public ApplicationException(String s) {
		// Call constructor of parent Exception
		super(s);
	}

	public ApplicationException(String s, Exception e) {
		// Call constructor of parent Exception
		super(s, e);
	}

	public ApplicationException(String s, Throwable e) {
		// Call constructor of parent Exception
		super(s, e);
	}
}
