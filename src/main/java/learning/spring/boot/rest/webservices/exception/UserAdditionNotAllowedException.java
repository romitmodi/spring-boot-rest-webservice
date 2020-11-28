package learning.spring.boot.rest.webservices.exception;

public class UserAdditionNotAllowedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserAdditionNotAllowedException(String message) {
		super(message);
	}

}
