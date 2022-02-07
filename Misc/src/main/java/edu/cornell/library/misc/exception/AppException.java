package edu.cornell.library.misc.exception;

/**
 * Base exception for errors arising while talking to an Application.
 * When thrown, it can optionally carry a string containing the
 * human readable error message, if any.
 * Sometimes it may be appropriate to display this error message
 * to the user. It can also carry the URL of the failed request.
 */
public class AppException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String errorMessage;
    private final String requestUrl;
    private final Object error;

    public AppException() {
        errorMessage = null;
        requestUrl = null;
        error = null;
    }

    public AppException(String errorString, String url) {
        super(String.format("Error from URL %s : %s", url, errorString));
        errorMessage = errorString;
        requestUrl = url;
        error = null;
    }

    public AppException(String errorString, String url, Object error) {
        errorMessage = errorString;
        requestUrl = url;
        this.error = error;
    }

    public Object getError() {
        return error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getRequestUrl() {
        return requestUrl;
    }
}
