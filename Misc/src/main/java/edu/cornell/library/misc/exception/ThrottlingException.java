package edu.cornell.library.misc.exception;

/**
 * Thrown if request returns a 403 response in response to too many API requests 
 */
public class ThrottlingException extends AppException {
    private static final long serialVersionUID = 1L;

    public ThrottlingException() {
        super();
    }

    public ThrottlingException(String errorString, String url) {
        super(errorString, url);
    }
}
