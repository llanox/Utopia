package co.edu.udea.ludens.exceptions;

public class DatabaseError extends RuntimeException {

	private static final long serialVersionUID = 1739544501L;

	public DatabaseError() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DatabaseError(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DatabaseError(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DatabaseError(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
