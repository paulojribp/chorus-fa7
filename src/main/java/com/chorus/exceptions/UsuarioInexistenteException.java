package com.chorus.exceptions;

public class UsuarioInexistenteException extends Exception {

	private static final long serialVersionUID = -8654435781090225602L;

	public UsuarioInexistenteException() {
	}

	public UsuarioInexistenteException(String arg0) {
		super(arg0);
	}

	public UsuarioInexistenteException(Throwable arg0) {
		super(arg0);
	}

	public UsuarioInexistenteException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
