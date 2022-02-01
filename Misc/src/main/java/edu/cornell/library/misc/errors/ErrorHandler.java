package edu.cornell.library.misc.errors;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

/**
 * This allows additional specific behaviour for handling errors.
 */
public interface ErrorHandler {

    boolean shouldHandle(HttpRequest httpRequest, HttpResponse httpResponse);

    void handle(HttpRequest httpRequest, HttpResponse httpResponse);
}
