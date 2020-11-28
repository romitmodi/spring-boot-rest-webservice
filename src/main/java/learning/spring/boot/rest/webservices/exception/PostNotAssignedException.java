package learning.spring.boot.rest.webservices.exception;

public class PostNotAssignedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PostNotAssignedException(String errorMessage) {
		super(errorMessage);
	}
}
