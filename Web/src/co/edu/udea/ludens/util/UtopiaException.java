package co.edu.udea.ludens.util;

public class UtopiaException extends Exception {

	private static final long serialVersionUID = 1739545095L;

	public UtopiaException(String msg, Exception e) {
		super(msg, e);
	}

	public UtopiaException(String msg) {
		super(msg);
	}
}
