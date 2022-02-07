package edu.cornell.library.misc.exception;

/**
 * Thrown if Request returns a 404 response.
 */
public class ObjectNotFoundException extends AppException {
    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException() {
        super();
    }

    public ObjectNotFoundException(String errorString, String url) {
        super(errorString, url);
    }
}
