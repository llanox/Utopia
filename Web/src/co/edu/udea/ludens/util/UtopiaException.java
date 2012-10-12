package co.edu.udea.ludens.util;

public class UtopiaException extends Exception {

	public UtopiaException(String msg, Exception e) {
		super(msg, e);
	}

	public UtopiaException(String msg) {
		super(msg);
	}
}
