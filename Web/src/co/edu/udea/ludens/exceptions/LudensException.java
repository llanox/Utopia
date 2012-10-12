package co.edu.udea.ludens.exceptions;

import co.edu.udea.ludens.enums.EnumMsgs;

public class LudensException extends Exception {

	public LudensException(String string) {
		super(string);
	}

	public LudensException(EnumMsgs error) {
		super(error.toString());
	}

	public LudensException(EnumMsgs EnumMsgError, Object... parameter) {
		super(String.format(EnumMsgError.toString(), parameter));

	}

	public LudensException(EnumMsgs error, Exception e) {
		super(error.toString(), e);
	}

	public LudensException(Exception e) {
		super(e);
	}
}
