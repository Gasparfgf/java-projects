package bame.domain.exception;

public abstract class DomainException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * @param message the error message.
     * */
	protected DomainException(final String message) {
        super(message);
    }

    /**
     * Constructor.
     * @param message the error message.
     * @param cause the throwable cause.
     * */
    protected DomainException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
