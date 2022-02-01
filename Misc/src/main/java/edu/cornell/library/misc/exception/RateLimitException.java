package edu.cornell.library.misc.exception;

/**
 * Thrown if Canvas returns a 403 Rate Limit Exceeded response.
 */
public class RateLimitException extends AppException {

    public RateLimitException() {
        super();
    }

    public RateLimitException(String canvasErrorString, String url) {
        super(canvasErrorString, url);
    }
}
