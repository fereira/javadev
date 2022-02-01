package edu.cornell.library.misc.exception;

/**
 * Base exception for errors arising while talking to Canvas.
 * When thrown, it can optionally carry a string containing the
 * human readable error message returned by Canvas, if any.
 * Sometimes it may be appropriate to display this error message
 * to the user.It can also carry the URL of the failed request.
 */
public class AppException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String canvasErrorMessage;
    private final String requestUrl;
    private final Object error;

    public AppException() {
        canvasErrorMessage = null;
        requestUrl = null;
        error = null;
    }

    public AppException(String canvasErrorString, String url) {
        super(String.format("Error from URL %s : %s", url, canvasErrorString));
        canvasErrorMessage = canvasErrorString;
        requestUrl = url;
        error = null;
    }

    public AppException(String canvasErrorString, String url, Object error) {
        canvasErrorMessage = canvasErrorString;
        requestUrl = url;
        this.error = error;
    }

    public Object getError() {
        return error;
    }

    public String getCanvasErrorMessage() {
        return canvasErrorMessage;
    }

    public String getRequestUrl() {
        return requestUrl;
    }
}
