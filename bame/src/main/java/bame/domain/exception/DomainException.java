package bame.domain.exception;

public abstract class DomainException extends RuntimeException {

    private static final long serialVersionUID = -2839273879523670874L;

	protected DomainException(String message) {
        super(message);
    }

    protected DomainException(String message, Throwable cause) {
        super(message, cause);
    }

}
