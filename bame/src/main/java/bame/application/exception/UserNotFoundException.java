package bame.application.exception;

public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -1L;

    /**
     * Constructor.
     * @param userId a user id.
     * */
	public UserNotFoundException(final String userId) {
		super("User not found with id:" + userId);
	}
}
