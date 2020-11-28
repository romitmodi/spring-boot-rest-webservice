package learning.spring.boot.rest.webservices.exception;

public class PostNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PostNotFoundException(String errorMessage) {
		super(errorMessage);
	}

}
