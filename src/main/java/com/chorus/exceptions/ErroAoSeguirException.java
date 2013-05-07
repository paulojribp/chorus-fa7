package com.chorus.exceptions;

public class ErroAoSeguirException extends Exception {

	private static final long serialVersionUID = 5403551152188205262L;

	public ErroAoSeguirException() {
	}

	public ErroAoSeguirException(String arg0) {
		super(arg0);
	}

	public ErroAoSeguirException(Throwable arg0) {
		super(arg0);
	}

	public ErroAoSeguirException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
