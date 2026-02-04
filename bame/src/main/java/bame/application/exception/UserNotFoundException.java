package bame.application.exception;

public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -1533252006853257211L;

	public UserNotFoundException(String userId) {
		super("User not found with id:" + userId);
	}
}
