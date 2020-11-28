package learning.spring.boot.rest.webservices.exception;

public class PostAlreadyAssignedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PostAlreadyAssignedException(String message) {
		super(message);
	}

}
