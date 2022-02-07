package edu.cornell.library.misc.exception;

/**
 * Thrown if Request returns a 403 Rate Limit Exceeded response.
 */
public class RateLimitException extends AppException {

    public RateLimitException() {
        super();
    }

    public RateLimitException(String errorString, String url) {
        super(errorString, url);
    }
}
