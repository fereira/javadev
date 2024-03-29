package edu.cornell.library.misc.errors;

import com.google.gson.Gson;

import edu.cornell.library.misc.exception.AppException;
import edu.cornell.library.misc.util.GsonResponseParser;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * The error handler that should be used when creation of an object fails.
 */
public class GenericErrorHandler implements ErrorHandler { 
    @Override
    public void handle(HttpRequest httpRequest, HttpResponse httpResponse) {
        Gson gson = GsonResponseParser.getDefaultGsonParser(false);
        try {
            String entityString = EntityUtils.toString(httpResponse.getEntity());
            GenericErrorResponse response = gson.fromJson(entityString, GenericErrorResponse.class);
            if (response.getErrors() != null) {
                throw new AppException("Failed to create user.", httpRequest.getRequestLine().getUri(), response);
            }
        } catch (IOException e) {
            // Ignore.
        }
    }
}
